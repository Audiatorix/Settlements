package roguecsdev.settlements;

import org.bukkit.Location;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


abstract class Territory
{
	String name;
	UUID owner;
	List<UUID> trusted;
	List<BoundingBox> area;
	List<Territory> children;
	// Note that this is the tax applied by the parent area to this area
	double tax;

	int getSize()
	{
		return 29 + name.getBytes().length + trusted.size() * 16 + area.size() * 48 +
			children.stream().mapToInt(c -> c.getSize()).sum();
	}

	void serializeInto(ByteBuffer b)
	{
		b.put((byte) name.getBytes().length);
		b.put(name.getBytes());

		b.putLong(owner.getMostSignificantBits());
		b.putLong(owner.getLeastSignificantBits());

		b.put((byte) trusted.size());
		for (UUID id : trusted)
		{
			b.putLong(id.getMostSignificantBits());
			b.putLong(id.getLeastSignificantBits());
		}

		b.put((byte) area.size());
		for (BoundingBox box : area)
		{
			box.serializeInto(b);
		}

		b.putShort((short) children.size());
		for (Territory c : children)
		{
			c.serializeInto(b);
		}
	}

	// Outermost territory last
	List<Territory> getMostPreciseTerritory(Location l)
	{
		for (Territory t : children)
		{
			if (t.containsLocation(l))
			{
				List<Territory> soFar = t.getMostPreciseTerritory(l);
				soFar.add(this);
				return soFar;
			}
		}
		return new ArrayList<>();
	}

	boolean containsLocation(Location l)
	{
		for (BoundingBox b : area)
		{
			if (b.containsLocation(l)) return true;
		}
		return false;
	}
}
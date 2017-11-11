package roguecsdev.settlements.Territories;

import org.bukkit.Location;
import roguecsdev.settlements.BoundingBox;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


abstract class Territory
{
	public String name;
	public UUID owner;
	public List<UUID> trusted;
	public List<BoundingBox> area;
	protected List<Territory> children;
	// Note that this is the tax applied by the parent area to this area
	public double tax;

	public abstract int getSize();

	protected int getBaseSize()
	{
		return 30 + name.getBytes().length + trusted.size() * 16 + area.size() * 24;
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

		if (!(this instanceof Duchy))
		{
			b.putDouble(tax);
		}

		if (this instanceof Settlement)
		{
			Settlement s = (Settlement) this;
			b.putInt(s.home.getBlockX());
			b.putInt(s.home.getBlockY());
			b.putInt(s.home.getBlockZ());
		}

		if (!(this instanceof Subplot))
		{
			b.putShort((short) children.size());
			for (Territory c : children)
			{
				c.serializeInto(b);
			}
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
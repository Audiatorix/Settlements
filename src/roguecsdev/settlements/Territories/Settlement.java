package roguecsdev.settlements.Territories;

import org.bukkit.Location;
import org.bukkit.World;

import roguecsdev.settlements.Utils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;


class Settlement extends Territory
{
	Location home;

	Settlement(ByteBuffer b, World world)
	{
		name = Utils.readStr(b);
		owner = Utils.readUUID(b);
		trusted = Utils.readUUIDs(b);
		area = Utils.readBounds(b);
		tax = b.getDouble();

		home = new Location(world, b.getDouble(), b.getDouble(), b.getDouble());

		Plot plots[] = new Plot[b.getShort()];
		for (short i = 0; i < plots.length; i++)
		{
			plots[i] = new Plot(b);
		}
		children = Arrays.stream(plots).collect(Collectors.toList());
	}

	public int getSize()
	{
		// add 24 for home location (sizeof double * 3)
		return getBaseSize() + 24 +
			children.stream().mapToInt(c -> c.getSize()).sum();
	}
}
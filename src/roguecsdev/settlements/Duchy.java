package roguecsdev.settlements;

import org.bukkit.Location;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class Duchy extends Territory
{
	Duchy(byte bytes[])
	{
		ByteBuffer b = ByteBuffer.wrap(bytes);

		name = Utils.readStr(b);
		owner = Utils.readUUID(b);
		trusted = Utils.readUUIDs(b);
		area = Utils.readBounds(b);
		// Tax doesn't apply to Duchies
		tax = 0;

		Plot plots[] = new Plot[b.getShort()];
		for (short i = 0; i < plots.length; i++)
		{
			plots[i] = new Plot(b);
		}
		children = Arrays.stream(plots).collect(Collectors.toList());
	}
}
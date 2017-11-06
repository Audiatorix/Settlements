package roguecsdev.settlements;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;


class Settlement extends Territory
{
	Settlement(ByteBuffer b)
	{
		name = Utils.readStr(b);
		owner = Utils.readUUID(b);
		trusted = Utils.readUUIDs(b);
		area = Utils.readBounds(b);
		tax = b.getDouble();

		Plot plots[] = new Plot[b.getShort()];
		for (short i = 0; i < plots.length; i++)
		{
			plots[i] = new Plot(b);
		}
		children = Arrays.stream(plots).collect(Collectors.toList());
	}
}
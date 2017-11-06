package roguecsdev.settlements;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;


class Plot extends Territory
{
	Plot(ByteBuffer b)
	{
		name = Utils.readStr(b);
		owner = Utils.readUUID(b);
		trusted = Utils.readUUIDs(b);
		area = Utils.readBounds(b);
		tax = b.getDouble();

		Subplot subplots[] = new Subplot[b.getShort()];
		for (short i = 0; i < subplots.length; i++)
		{
			subplots[i] = new Subplot(b);
		}
		children = Arrays.stream(subplots).collect(Collectors.toList());
	}


}
package roguecsdev.settlements;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


class Settlement extends Territory
{
	private List<Plot> children;

	Settlement(byte bytes[])
	{
		ByteBuffer b = ByteBuffer.wrap(bytes);

		name = Utils.readStr(b);
		owner = Utils.readUUID(b);
		trusted = Utils.readUUIDs(b);
		area = Utils.readBounds(b);

		children = new ArrayList<>();
		for (short plots = b.getShort(); plots > 0; plots--)
		{
			String plotName = Utils.readStr(b);
			UUID plotOwner = Utils.readUUID(b);
			List<UUID> plotTrusted = Utils.readUUIDs(b);
			List<BoundingBox> plotArea = Utils.readBounds(b);

			Subplot subplots[] = new Subplot[b.get()];
			for (int i = subplots.length; i > 0; i--)
			{
				String subplotName = Utils.readStr(b);
				UUID subplotOwner = Utils.readUUID(b);
				List<UUID> subplotTrusted = Utils.readUUIDs(b);
				List<BoundingBox> subplotArea = Utils.readBounds(b);

				subplots[i] = new Subplot(subplotName, subplotOwner, subplotTrusted, subplotArea);
			}
			List<Subplot> subplotsList = Arrays.stream(subplots).collect(Collectors.toList());

			children.add(new Plot(plotName, owner, plotTrusted, plotArea, subplotsList, this));
		}
	}
}
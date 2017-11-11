package roguecsdev.settlements.Territories;

import org.bukkit.World;

import roguecsdev.settlements.Utils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;


class Duchy extends Territory
{
	Duchy(byte bytes[], World world)
	{
		ByteBuffer b = ByteBuffer.wrap(bytes);

		name = Utils.readStr(b);
		owner = Utils.readUUID(b);
		trusted = Utils.readUUIDs(b);
		area = Utils.readBounds(b);
		// Tax doesn't apply to Duchies
		tax = 0;

		Settlement stlmts[] = new Settlement[b.getShort()];
		for (short i = 0; i < stlmts.length; i++)
		{
			stlmts[i] = new Settlement(b, world);
		}
		children = Arrays.stream(stlmts).collect(Collectors.toList());
	}

	public int getSize()
	{
		// subtract 8 for the lack of a tax rate
		return getBaseSize() - 8 +
			children.stream().mapToInt(c -> c.getSize()).sum();
	}
}
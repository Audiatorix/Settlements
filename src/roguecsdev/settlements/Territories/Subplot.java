package roguecsdev.settlements.Territories;

import roguecsdev.settlements.Utils;

import java.nio.ByteBuffer;
import java.util.ArrayList;


class Subplot extends Territory
{
	Subplot(ByteBuffer b)
	{
		name = Utils.readStr(b);
		owner = Utils.readUUID(b);
		trusted = Utils.readUUIDs(b);
		area = Utils.readBounds(b);
		tax = b.getDouble();

		// Shall remain empty
		children = new ArrayList<>();
	}

	public int getSize()
	{
		return getBaseSize() - 1;
	}
}
package roguecsdev.settlements;

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

	@Override
	int getSize()
	{
		return 28 + name.getBytes().length + trusted.size() * 16 + area.size() * 48;
	}
}
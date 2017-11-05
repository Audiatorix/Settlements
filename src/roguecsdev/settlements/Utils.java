package roguecsdev.settlements;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


class Utils
{
// SECTION settlement file parsing helpers
	static String readStr(ByteBuffer b)
	{
		byte strData[] = new byte[b.get()];
		b.get(strData);
		return String.valueOf(strData);
	}

	static List<BoundingBox> readBounds(ByteBuffer b)
	{
		BoundingBox boxes[] = new BoundingBox[b.get()];
		for (byte i = 0; i < boxes.length; i++)
		{
			boxes[i] = new BoundingBox(b);
		}
		return Arrays.stream(boxes).collect(Collectors.toList());
	}

	static UUID readUUID(ByteBuffer b)
	{
		byte data[] = new byte[16];
		b.get(data);
		return UUID.nameUUIDFromBytes(data);
	}

	static List<UUID> readUUIDs(ByteBuffer b)
	{
		UUID uuids[] = new UUID[b.get()];
		for (short num = b.getShort(); num > 0; num--)
		{
			uuids[num] = Utils.readUUID(b);
		}
		return Arrays.stream(uuids).collect(Collectors.toList());
	}
// END SECTION
}
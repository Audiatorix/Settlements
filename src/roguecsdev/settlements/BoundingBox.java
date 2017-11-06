package roguecsdev.settlements;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.nio.ByteBuffer;


class BoundingBox
{
	private long sx, sy, sz, ex, ey, ez;

	BoundingBox(Location first, Location second)
	{
		sx = first.getBlockX();
		sy = first.getBlockY();
		sz = first.getBlockZ();
		ex = second.getBlockX();
		ey = second.getBlockY();
		ez = second.getBlockZ();

		// swap if out of order
		long temp;
		if (sx > ex)
		{
			temp = sx;
			sx = ex;
			ex = temp;
		}
		if (sy > ey)
		{
			temp = sy;
			sy = ey;
			ey = temp;
		}
		if (sz > ez)
		{
			temp = sz;
			sz = ez;
			ez = temp;
		}
	}

	BoundingBox(ByteBuffer b)
	{
		sx = b.getLong();
		sy = b.getLong();
		sz = b.getLong();
		ex = b.getLong();
		ey = b.getLong();
		ez = b.getLong();
	}

	BoundingBox(BoundingBox source)
	{
		sx = source.sx;
		sy = source.sy;
		sz = source.sz;
		ex = source.ex;
		ey = source.ey;
		ez = source.ez;
	}

	void serializeInto(ByteBuffer b)
	{
		b.putLong(sx);
		b.putLong(sy);
		b.putLong(sz);
		b.putLong(ex);
		b.putLong(ey);
		b.putLong(ez);
	}

	boolean overlaps(BoundingBox other)
	{
		return ex >= other.sx && other.ex >= sx
			&& ey >= other.sy && other.ey >= sy
			&& ey >= other.sy && other.ey >= sy;
	}

	boolean containsBox(BoundingBox other)
	{
		return sx <= other.sx && ex >= other.ex
			&& sy <= other.sy && ey >= other.ey
			&& sz <= other.sz && ez >= other.ez;
	}

	boolean containsLocation(Location l)
	{
		return sx <= l.getBlockX() && ex >= l.getBlockX()
			&& sy <= l.getBlockY() && ey >= l.getBlockY()
			&& sz <= l.getBlockZ() && ez >= l.getBlockZ();
	}
}
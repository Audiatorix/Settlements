package roguecsdev.settlements;

import org.bukkit.Location;

import java.nio.ByteBuffer;


public class BoundingBox
{
	private int sx, sy, sz, ex, ey, ez;

	public BoundingBox(Location first, Location second)
	{
		sx = first.getBlockX();
		sy = first.getBlockY();
		sz = first.getBlockZ();
		ex = second.getBlockX();
		ey = second.getBlockY();
		ez = second.getBlockZ();

		// swap if out of order
		int temp;
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

	public BoundingBox(ByteBuffer b)
	{
		sx = b.getInt();
		sy = b.getInt();
		sz = b.getInt();
		ex = b.getInt();
		ey = b.getInt();
		ez = b.getInt();
	}

	public BoundingBox(BoundingBox source)
	{
		sx = source.sx;
		sy = source.sy;
		sz = source.sz;
		ex = source.ex;
		ey = source.ey;
		ez = source.ez;
	}

	public void serializeInto(ByteBuffer b)
	{
		b.putInt(sx);
		b.putInt(sy);
		b.putInt(sz);
		b.putInt(ex);
		b.putInt(ey);
		b.putInt(ez);
	}

	public boolean overlaps(BoundingBox other)
	{
		return ex >= other.sx && other.ex >= sx
			&& ey >= other.sy && other.ey >= sy
			&& ey >= other.sy && other.ey >= sy;
	}

	public boolean containsBox(BoundingBox other)
	{
		return sx <= other.sx && ex >= other.ex
			&& sy <= other.sy && ey >= other.ey
			&& sz <= other.sz && ez >= other.ez;
	}

	public boolean containsLocation(Location l)
	{
		return sx <= l.getBlockX() && ex >= l.getBlockX()
			&& sy <= l.getBlockY() && ey >= l.getBlockY()
			&& sz <= l.getBlockZ() && ez >= l.getBlockZ();
	}

	public void extend(Direction direction, int amount)
	{
		switch (direction)
		{
			case UP:
				ey += amount;
			case DOWN:
				sy -= amount;
			case EAST:
				ex += amount;
			case WEST:
				sx -= amount;
			case SOUTH:
				ez += amount;
			case NORTH:
				sz -= amount;
		}
	}

	public enum Direction
	{
		UP, DOWN, EAST, WEST, SOUTH, NORTH
	}
}
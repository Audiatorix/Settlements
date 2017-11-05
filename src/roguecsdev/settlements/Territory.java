package roguecsdev.settlements;

import java.util.List;
import java.util.UUID;


abstract class Territory
{
	protected String name;
	protected UUID owner;
	protected List<UUID> trusted;
	protected List<BoundingBox> area;
}
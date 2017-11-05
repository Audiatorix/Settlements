package roguecsdev.settlements;

import java.util.List;
import java.util.UUID;


class Plot extends Territory
{
	private Settlement parent;
	private List<Subplot> children;

	Plot(String name, UUID owner, List<UUID> trusted, List<BoundingBox> area, List<Subplot> children,
	     Settlement parent)
	{
		this.name = name;
		this.owner = owner;
		this.trusted = trusted;
		this.area = area;
		this.children = children;
		this.parent = parent;

		for (Subplot child : children)
		{
			child.setParent(this);
		}
	}
}
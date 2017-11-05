package roguecsdev.settlements;

import java.util.List;
import java.util.UUID;


class Subplot extends Territory
{
	private Plot parent;

	Subplot(String name, UUID owner, List<UUID> trusted, List<BoundingBox> area)
	{
		this.name = name;
		this.owner = owner;
		this.trusted = trusted;
		this.area = area;
	}

	void setParent(Plot parent)
	{
		this.parent = parent;
	}
}
package roguecsdev.settlements;

import java.util.List;
import java.util.UUID;


class Subplot extends Territory
{
	private Plot parent;
	Settlement settlement;

	Subplot(String name, UUID owner, List<UUID> trusted, List<BoundingBox> area)
	{
		this.name = name;
		this.owner = owner;
		this.trusted = trusted;
		this.area = area;
	}

	void setParents(Plot parent, Settlement settlement)
	{
		this.parent = parent;
		this.settlement = settlement;
	}
}
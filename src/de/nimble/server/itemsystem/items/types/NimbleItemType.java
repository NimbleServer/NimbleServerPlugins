package de.nimble.server.itemsystem.items.types;

public enum NimbleItemType {

	WEAPON,
	USE,
	NONE
	;

	private NimbleItemType() {

	}

	public static NimbleItemType getTypeByName(String name) {
		for(NimbleItemType type : values()) {
			if(name.equals(type.toString())) {
				return type;
			}
		}
		return NONE;
	}

}
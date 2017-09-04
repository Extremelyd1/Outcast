package item;

import org.newdawn.slick.Image;

public class Item {

	private ItemType type;
	
	private Image texture;
	
	public Item(ItemType type, Image texture) {
		
		this.type = type;
		this.texture = texture;
		
	}
	
	public ItemType getType() {
		return type;
	}
	
	public Image getTexture() {
		return texture;
	}
	
}

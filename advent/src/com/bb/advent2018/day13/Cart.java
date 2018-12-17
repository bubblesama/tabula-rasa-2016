package com.bb.advent2018.day13;

public class Cart {
	
	public int id;
	public int i;
	public int j;
	public boolean isCrashed;
	public CartTurn nextTurn;
	public Direction direction;
	
	public Cart(int id, int i, int j, Direction direction) {
		super();
		this.id = id;
		this.i = i;
		this.j = j;
		this.direction = direction;
		this.isCrashed = false;
		this.nextTurn = CartTurn.LEFT;
	}
	
	public void step(CartMap map) {
		if (!this.isCrashed) {
			//move
			this.i += this.direction.di;
			this.j += this.direction.dj;
			//check crash
			this.isCrashed = map.shouldCrash(this.id, this.i, this.j);
			//check direction
			if (!this.isCrashed) {
				Direction newDirection = null;
				CartTurn turn = null;
				switch (map.getCell(i, j).track) {
					
				case DOWNLEFT:
						switch (this.direction) {
						case RIGHT:
							newDirection = Direction.DOWN;
							break;
						case UP:
							newDirection = Direction.LEFT;
							break;
						default:
							break;
						}
					break;
				
				case RIGHTDOWN:
					switch (this.direction) {
					case LEFT:
						newDirection = Direction.DOWN;
						break;
					case UP:
						newDirection = Direction.RIGHT;
						break;
					default:
						break;
					}
				break;
			
				case UPLEFT:
					switch (this.direction) {
					case RIGHT:
						newDirection = Direction.UP;
						break;
					case DOWN:
						newDirection = Direction.LEFT;
						break;
					default:
						break;
					}
				break;
					
				case UPRIGHT:
					switch (this.direction) {
					case LEFT:
						newDirection = Direction.UP;
						break;
					case DOWN:
						newDirection = Direction.RIGHT;
						break;
					default:
						break;
					}
				break;
					
				case CROSS:
					newDirection = this.direction.turn(this.nextTurn);
					turn = this.nextTurn.next();
					break;
				default:
					break;
				}
				if (turn != null) {
					this.nextTurn = turn;
				}
				if (newDirection != null) {
					this.direction = newDirection;
				}
			}
		}
	}

}

//Author , Lcass / Lucas Spencer
//This software is designed to function as a wrapper for specific LWJGL/OpenGL bindings.
//The software wraps certain vbo creation functions and allows sprite batching methods.
// Copyright (C)  2015  Lucas Spencer
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.
package wrapper.graphics.texture;

public class spritecomponent {
	public spritecomponent(){
		
	}
	/**
	 * Initialize a new sprite component, usually handled automatically
	 * @param x X position
	 * @param y Y position
	 * @param ex End X position
	 * @param ey End Y position
	 * @param t Texture object
	 */
	public spritecomponent(float x, float y , float ex , float ey , Texture t){
		this.x = x;
		this.y = y;
		this.ex = ex;
		this.ey = ey;
		this.t = t;
	}
	/**
	 * Modify the current data
	 * @param x X position
	 * @param y Y position
	 * @param ex End X position
	 * @param ey End Y postion
	 * @param t Texture object
	 */
	public void set(float x, float y, float ex, float ey,Texture t){
		this.x = x;
		this.y = y;
		this.ex = ex;
		this.ey = ey;
		this.t = t;
		
	}
	public float x,y,ex,ey;
	public Texture t;
}

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

import org.lwjgl.opengl.GL11;

public class spritesheet {
	private Texture tex;
	private float x,y,ex,ey,width,height;
	public spritesheet(String location){
		tex = Texture.loadTexture(location);
		this.width = tex.width;
		this.height = tex.height;
	}
	public spritecomponent getcoords(int x, int y, int ex,int ey){
		spritecomponent temp= new spritecomponent();
		this.x = x/width;
		this.y = y/height;
		this.ex = ex/width;
		this.ey = ey/height;
		temp.set(this.x, this.y, this.ex, this.ey, tex);
		return temp;
	}
	public Texture gettexture(){
		return tex;
	}

}

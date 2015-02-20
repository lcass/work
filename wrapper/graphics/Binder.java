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
package wrapper.graphics;

import wrapper.utilitys.ProgressiveBuffer;
import wrapper.utilitys.Vertex2d;
import wrapper.utilitys.Vertex3d;

public class Binder {
	private boolean update = false;
	public Binder(){
		
	}
	public void clear(){
		
	}
	public void bind_vertices(Vertex2d data){
		
	}
	public void bind_vertices(Vertex3d data){
		
	}
	public void bind_vertices(ProgressiveBuffer[] data){
		
	}
	public void render(){
		if(update){
			update();
		}
	}
	public void update(){
		
	}
	public void cleanup(){
		
	}
	public void rotate(){
		
	}
	public void set_rotation_centre(){
		
	}
	public void translate(Vertex2d translation){
		
	}
	public void translate(Vertex3d translation){
		
	}

}

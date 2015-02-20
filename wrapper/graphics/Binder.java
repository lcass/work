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
import wrapper.vbo.Vbo;

public class Binder {
	private boolean update = false;
	private ProgressiveBuffer[] data;
	private int vbo_size = 12;
	private Vbo Renderable;
	public Binder(Shader s,int width, int height,boolean threed){
		Renderable = new Vbo(width,height,s);
		Renderable.create(12);
		if(threed){
			data[0] = new ProgressiveBuffer(new Vertex3d[]{},false,true);
		}
		else{
			data[0] = new ProgressiveBuffer(new Vertex2d[]{},false,false);
		}
		data[1] = new ProgressiveBuffer(new Vertex2d[]{},true,false);
	}
	public void force_create(int size){
		Renderable.dispose();
		Renderable.create(size);
	}
	public void set_shader(Shader s){
		Renderable.set_shader(s);
	}
	public void clear(){
		data[0].clear();
		data[1].clear();
		Renderable.clear_data();
	}
	
	public int bind_vertices(Vertex2d[] data){
		update = true;
		if(this.data[0].threed){
			System.out.println("Cannot bind , this is 2d data!");
			return -1;
		}
		ProgressiveBuffer[] temp = wrapper.utilitys.Utility.to_progressive(data);
		int position = this.data[0].get_limit();
		this.data[0].extend(temp[0]);
		this.data[1].extend(temp[1]);
		return position;
	}
	public int bind_vertices(Vertex3d[] data){
		update = true;
		if(!this.data[0].threed){
			System.out.println("Cannot bind , this is 3d data!");
			return -1;
		}
		ProgressiveBuffer[] temp = wrapper.utilitys.Utility.to_progressive(data);
		int position = this.data[0].get_limit();
		this.data[0].extend(temp[0]);
		this.data[1].extend(temp[1]);
		return position;
	}
	public int bind_vertices(ProgressiveBuffer[] data){
		update = true;
		if(this.data[0].threed != data[0].threed){
			System.out.println("The data types do not match");
			return -1;
		}
		int position = this.data[0].get_limit();
		this.data[0].extend(data[0]);
		this.data[1].extend(data[1]);
		return position;
	}
	public void bind_index(Vertex2d[] data, int index){
		update = true;
		if(this.data[0].threed){
			System.out.println("Cannot bind , this is 2d data!");
			return;
		}
		ProgressiveBuffer[] temp = wrapper.utilitys.Utility.to_progressive(data);
		this.data[0].index_put(index, temp[0]);
		this.data[1].index_put(index, temp[1]);
	}
	public void bind_index(Vertex3d[] data, int index){
		update = true;
		if(!this.data[0].threed){
			System.out.println("Cannot bind , this is 3d data!");
			return;
		}
		int index_2d = ((index + 1) * 3)/2;
		ProgressiveBuffer[] temp = wrapper.utilitys.Utility.to_progressive(data);
		this.data[0].index_put(index, temp[0]);
		this.data[1].index_put(index_2d, temp[1]);
	}
	public void bind_index(ProgressiveBuffer[] data,int index){
		update = true;
		if(index == -1){
			System.out.println("Invalid index!");
			return;
		}
		this.data[0].index_put(index,data[0]);
		this.data[1].index_put(index,data[1]);
	}
	public void render(){
		if(update){
			update();
		}
	}
	public void update(){
		if(data[0].get_limit() > vbo_size){
			Renderable.rebind(data[0].get_limit());
		}
		Renderable.edit_data(data);
	}
	public void cleanup(){
		Renderable.dispose();
		data[0].clear();
		data[1].clear();
	}
	public void rotate(Vertex3d rotation){
		Renderable.rotate(rotation.x,rotation.y,rotation.z);
	}
	public void set_rotation_centre(Vertex3d position){
		Renderable.set_rotation_point(position);
	}
	public void translate(Vertex2d translation){
		Renderable.set_position(new Vertex3d(translation.x,translation.y,1));
	}
	public void translate(Vertex3d translation){
		Renderable.set_position(translation);
	}

}

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

import java.nio.FloatBuffer;

import wrapper.utilitys.ProgressiveBuffer;
import wrapper.utilitys.Vertex2d;
import wrapper.utilitys.Vertex3d;
import wrapper.vbo.Vbo;

public class Binder {
	private boolean update = false;
	private ProgressiveBuffer[] data = new ProgressiveBuffer[2];
	private int vbo_size = 12;
	private Vbo Renderable;
	/**
	 * Creates a Vbo binding object.
	 * @param s This is a shader created on the user side
	 * @param width This is the current screen width in pixels
	 * @param height This is the current screen height in pixels
	 * @param threed Is it a 3d Binder object? Make sure this is correct or a lot of things go wrong
	 */
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
	/**
	 * Create a VBO of a specific size in verticies by force , the 3d value is still the same.
	 * @param size The number of vertices.
	 */
	public void force_create(int size){
		Renderable.dispose();
		Renderable.create(size);
		update = true;
	}
	/**
	 * Bind a custom shader after the vbo was created
	 * @param s The name of the shader
	 */
	public void set_shader(Shader s){
		Renderable.set_shader(s);
	}
	/**
	 * Clear all currently stored data and reset the vbo
	 */
	public void clear(){
		data[0].clear();
		data[1].clear();
		Renderable.clear_data();
	}
	/**
	 * Bind a set of Vertex2d[] into the current buffer , these are appended and do not modify previous data
	 * @param data The vertex data
	 * @return Returns the position of this data within the current buffer
	 */
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
	/**
	 * Bind a set of Vertex3d[] into the current buffer , these are appended and do not modify previous data
	 * @param data The vertex data
	 * @return Returns the position of this data within the current buffer
	 */
	public int bind_vertices(Vertex3d[] data){
		update = true;
		if(!this.data[0].threed){
			System.out.println("Cannot bind , this is 3d data!");
			return -1;
		}
		ProgressiveBuffer[] temp = wrapper.utilitys.Utility.to_progressive(data);
		int position = this.data[0].get_limit();
		FloatBuffer tempbuff = temp[0].get_data();
		tempbuff.rewind();
		
		this.data[0].extend(temp[0]);
		this.data[1].extend(temp[1]);
		return position;
	}
	/**
	 * Bind a progressive buffer of the data to the current buffer , make sure it is of the same data type.
	 * @param data The progressive buffers , the first is the vertex and the second is the texture buffer.
	 * @return Returns the current position of this data within the buffer , this is correctly calculated for both depending on 3d or not
	 */
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
	/** 
	 * Bind vertex data at a specific offset from the beggining of the buffer.
	 * This must be less than the buffer length.
	 * @param data This is the vertex data that will be appended to the buffer
	 * @param index This is the offset from the front of the buffer.
	 */
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
	/**
	 * Bind Vertex data at a specific offset from the beggining of the buffer.
	 * This must be less than the buffer length.
	 * @param data Data that is to be bound
	 * @param index Current offset from the start of the array.
	 */
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
	/**
	 * Bind a progressive_buffer at a specific offset from the beggining of the buffer.
	 * This must be less than the buffer length.
	 * @param data Data that is to be bound
	 * @param index Current offset from the start of the array.
	 */
	public void bind_index(ProgressiveBuffer[] data,int index){
		update = true;
		if(index == -1){
			System.out.println("Invalid index!");
			return;
		}
		this.data[0].index_put(index,data[0]);
		this.data[1].index_put(index,data[1]);
	}
	/**
	 * render stored data and update the buffer if it is needed
	 */
	public void render(){
		if(update){
			update();
		}
		Renderable.render();
	}
	/**
	 * Automatically called , override function if you are rendering the vbo seperately.
	 */
	public void update(){
		if(data[0].get_limit() > vbo_size){
			Renderable.rebind(data[0].get_limit());
		}
		Renderable.edit_data(data);
		update = false;
	}
	/**
	 * Destroy and cleanup the current binder , must be called on program exit.
	 */
	public void cleanup(){
		Renderable.dispose();
		data[0].clear();
		data[1].clear();
	}
	/**
	 * Apply a rotation to the Vbo
	 * @param rotation Rotation int the form x y z
	 */
	public void rotate(Vertex3d rotation){
		Renderable.rotate(rotation.x,rotation.y,rotation.z);
	}
	/**
	 * Set the rotation point of the current vbo
	 * @param position The position of the rotation point
	 */
	public void set_rotation_centre(Vertex3d position){
		Renderable.set_rotation_point(position);
	}
	/**
	 * Set the current addition to the positions stored in the buffer
	 * @param translation a vertex2d form , for 2d usage
	 */
	public void translate(Vertex2d translation){
		Renderable.set_position(new Vertex3d(translation.x,translation.y,0));
	}
	/**
	 * Set the current addition to the positions stored in the buffer
	 * @param translation a vertex3d form , for 3d usage
	 */
	public void translate(Vertex3d translation){
		Renderable.set_position(translation);
	}

}

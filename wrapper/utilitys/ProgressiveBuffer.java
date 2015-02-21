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

package wrapper.utilitys;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class ProgressiveBuffer {
	private FloatBuffer data, temp;
	private boolean texturised = false;
	private int setsize = 0;
	private int limit = 0;
	public boolean threed = false;
	/**
	 * Create a new progressivebuffer
	 * @param data The data to start the buffer , if null the buffer will be initialized empty
	 * @param texturised Is this a texture object , if it is in position [1] then it is.
	 * @param threed Is this a 3d progressive buffer , False if this is a texture object
	 */
	public ProgressiveBuffer(Vertex2d[] data, boolean texturised, boolean threed) {
		this.texturised = texturised;
		this.threed = threed;
		if (texturised) {
			threed = false;
		}
		if (data != null) {
			setsize = data.length * 2;
			this.data = BufferUtils.createFloatBuffer(data.length * 2);
			this.temp = BufferUtils.createFloatBuffer(data.length * 2);
			if (!texturised) {

				for (int i = 0; i < data.length; i++) {
					this.data.put(data[i].x);
					this.data.put(data[i].y);
					limit += 2;
				}
			} else {
				for (int i = 0; i < data.length; i++) {
					this.data.put(data[i].u);
					this.data.put(data[i].v);
					limit += 2;
				}
			}
		} else {
			this.data = BufferUtils.createFloatBuffer(0);
			this.temp = BufferUtils.createFloatBuffer(0);
		}

	}
	/**
	 * Create a new progressivebuffer
	 * @param data The data to start the buffer , if null the buffer will be initialized empty
	 * @param texturised Is this a texture object , if it is in position [1] then it is.
	 * @param threed Is this a 3d progressive buffer , False if this is a texture object
	 */
	public ProgressiveBuffer(Vertex3d[] data, boolean texturised, boolean threed) {
		this.texturised = texturised;
		this.threed = threed;
		if (texturised) {
			threed = false;
		}
		if (data != null) {

			if (!texturised) {
				setsize = data.length * 3;
				this.data = BufferUtils.createFloatBuffer(data.length * 3);
				this.temp = BufferUtils.createFloatBuffer(data.length * 3);
				for (int i = 0; i < data.length; i++) {
					this.data.put(data[i].x);
					this.data.put(data[i].y);
					this.data.put(data[i].z);
					limit += 3;
				}
			} else {
				setsize = data.length * 2;
				this.data = BufferUtils.createFloatBuffer(data.length * 2);
				this.temp = BufferUtils.createFloatBuffer(data.length * 2);
				for (int i = 0; i < data.length; i++) {
					this.data.put(data[i].u);
					this.data.put(data[i].v);
					limit += 2;
				}
			}
		} else {
			this.data = BufferUtils.createFloatBuffer(0);
			this.temp = BufferUtils.createFloatBuffer(0);
		}

	}
	/**
	 * Create a new progressivebuffer
	 * @param data The data to start the buffer , if null the buffer will be initialized empty
	 * @param texturised Is this a texture object , if it is in position [1] then it is.
	 * @param threed Is this a 3d progressive buffer , False if this is a texture object
	 */
	public void create(Vertex2d[] data, boolean texturised) {
		if (threed && !texturised) {
			System.out.println("This is a 3d progressive buffer!");
			return;
		}
		this.texturised = texturised;
		this.data = BufferUtils.createFloatBuffer(data.length * 2);
		this.temp = BufferUtils.createFloatBuffer(data.length * 2);
		if (!texturised) {
			for (int i = 0; i < data.length; i++) {
				this.data.put(data[i].x);
				this.data.put(data[i].y);
				limit += 2;
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				this.data.put(data[i].u);
				this.data.put(data[i].v);
				limit += 2;
			}
		}
	}
	/**
	 * Create a new progressivebuffer
	 * @param data The data to start the buffer , if null the buffer will be initialized empty
	 * @param texturised Is this a texture object , if it is in position [1] then it is.
	 * @param threed Is this a 3d progressive buffer , False if this is a texture object
	 */
	public void create(Vertex3d[] data, boolean texturised) {
		if (!threed && !texturised) {
			System.out.println("This is a 2d progressive_buffer!");
			return;
		}
		this.texturised = texturised;

		if (!texturised) {
			this.data = BufferUtils.createFloatBuffer(data.length * 3);
			this.temp = BufferUtils.createFloatBuffer(data.length * 3);
			for (int i = 0; i < data.length; i++) {
				this.data.put(data[i].x);
				this.data.put(data[i].y);
				this.data.put(data[i].z);
				limit += 3;
			}
		} else {
			for (int i = 0; i < data.length; i++) {
				this.data.put(data[i].u);
				this.data.put(data[i].v);
				limit += 2;
			}
		}
	}
	/**
	 * Extend the current ProgressiveBuffer by appending the data to the end
	 * @param data Data in the form Vertex2d to be appended to the end
	 */
	public void extend(Vertex2d[] data) {
		if (threed) {
			System.out.println("This is a 3d progressive_buffer!");
			return;
		}
		float[] temp = new float[setsize];
		this.data.rewind();
		this.data.get(temp);
		float[] currtemp = new float[temp.length + (data.length * 2)];
		for (int i = 0; i < temp.length; i++) {
			currtemp[i] = temp[i];
		}
		if (!texturised) {
			for (int i = 0; i < data.length * 2; i += 2) {
				currtemp[i + temp.length] = data[i / 2].x;
				currtemp[i + temp.length + 1] = data[i / 2].y;
				limit += 2;
			}
		} else {
			for (int i = 0; i < data.length * 2; i += 2) {
				currtemp[i + temp.length] = data[i / 2].u;
				currtemp[i + 1 + temp.length] = data[i / 2].v;
				limit += 2;
			}
		}
		this.data = BufferUtils.createFloatBuffer(currtemp.length);
		setsize = currtemp.length;
		this.data.put(currtemp);
		this.data.rewind();
	}
	/**
	 * Extend the current progressive buffer with the data.
	 * @param data The Vertex3d[] data to append to the buffer.
	 */
	public void extend(Vertex3d[] data) {
		if (!threed) {
			System.out.println("This is a 2d progressive_buffer!");
			return;
		}
		float[] temp = new float[setsize];
		this.data.rewind();
		this.data.get(temp);
		float[] currtemp;

		if (!texturised) {
			currtemp = new float[temp.length + (data.length * 3)];
			for (int i = 0; i < temp.length; i++) {
				currtemp[i] = temp[i];
			}
			for (int i = 0; i < data.length * 3; i += 3) {
				currtemp[i + temp.length] = data[i / 3].x;
				currtemp[i + temp.length + 1] = data[i / 3].y;
				currtemp[i + temp.length + 2] = data[i / 3].z;
				limit += 3;
			}
		} else {
			currtemp = new float[temp.length + (data.length * 2)];
			for (int i = 0; i < temp.length; i++) {
				currtemp[i] = temp[i];
			}
			for (int i = 0; i < data.length * 2; i += 2) {
				currtemp[i + temp.length] = data[i / 2].u;
				currtemp[i + 1 + temp.length] = data[i / 2].v;
				limit += 2;
			}
		}
		this.data = BufferUtils.createFloatBuffer(currtemp.length);
		setsize = currtemp.length;
		this.data.put(currtemp);
		this.data.rewind();
	}
	/**
	 * Extend the current progressive buffer.
	 * @param indata ProgressiveBuffer to append to the end of the current data
	 */
	public void extend(ProgressiveBuffer indata) {

		if (!indata.threed || indata.texturised) {
			float[] temp = new float[setsize];
			FloatBuffer indatanew = indata.get_data();
			Vertex2d[] data = new Vertex2d[indata.get_data().capacity() / 2];
			indatanew.rewind();
			for (int i = 0; i < indata.get_data().capacity() / 2; i++) {
				float x = indatanew.get();
				float y = indatanew.get();

				data[i] = new Vertex2d(x, y);
				limit += 2;
			}
			this.data.rewind();

			this.data.get(temp);

			float[] currtemp = new float[temp.length + (data.length * 2)];
			for (int i = 0; i < temp.length; i++) {
				currtemp[i] = temp[i];
			}

			for (int i = 0; i < data.length * 2; i += 2) {
				currtemp[i + temp.length] = data[i / 2].x;
				currtemp[i + temp.length + 1] = data[i / 2].y;
			}

			this.data = BufferUtils.createFloatBuffer(currtemp.length);
			setsize = currtemp.length;
			this.data.put(currtemp);
			this.data.rewind();
			indatanew.clear();
			indatanew = null;
			data = null;
			temp = null;
		} else {
			float[] temp = new float[setsize];
			FloatBuffer indatanew = indata.get_data();
			Vertex3d[] data = new Vertex3d[indata.get_data().capacity() / 3];
			indatanew.rewind();
			for (int i = 0; i < indata.get_data().capacity() / 3; i++) {
				float x = indatanew.get();
				float y = indatanew.get();
				float z = indatanew.get();

				data[i] = new Vertex3d(x, y, z);
				limit += 3;
			}
			this.data.rewind();

			this.data.get(temp);

			float[] currtemp = new float[temp.length + (data.length * 3)];
			for (int i = 0; i < temp.length; i++) {
				currtemp[i] = temp[i];
			}

			for (int i = 0; i < data.length * 3; i += 3) {
				currtemp[i + temp.length] = data[i / 3].x;
				currtemp[i + temp.length + 1] = data[i / 3].y;
				currtemp[i + temp.length + 2] = data[i / 3].z;
			}

			this.data = BufferUtils.createFloatBuffer(currtemp.length);
			setsize = currtemp.length;
			this.data.put(currtemp);
			this.data.rewind();
			indatanew.clear();
			indatanew = null;
			data = null;
			temp = null;
		}
	}
	/**
	 * Obtain the FloatBuffer
	 * @return Returns the actual FloatBuffer
	 */
	public FloatBuffer get_data() {

		return data;
	}
	/**
	 * Reset the current position of the FloatBuffer to 0
	 */
	public void rewind() {
		data.rewind();
	}
	/**
	 * Place data at a specific position in the progressive_Buffer
	 * @param index This is the offset of the data from the beggining of the buffer 
	 * @param indata This is the data to be added to the buffer, overwrites already stored data in the positions.
	 */
	public void index_put(int index, ProgressiveBuffer indata) {
		if (indata.threed && !threed) {
			System.out.println("This is a 2d progressive_buffer!");
			return;
		}
		if (!indata.threed && threed) {
			System.out.println("This is a 3d progressive_buffer!");
			return;
		}
		FloatBuffer temp = indata.get_data();
		temp.rewind();
		for (int i = 0; i < temp.capacity(); i++) {

			this.data.put(index + i, temp.get());
		}
	}
	/**
	 * Clear the buffer and reset the limit and positions.
	 */
	public void clear() {
		setsize = 0;
		limit = 0;
		if (this.data != null) {
			this.data.clear();

		}
		if (this.temp != null) {
			this.temp.clear();
		}

		texturised = false;
	}
	/**
	 * Returns the limit of the buffer
	 * @return Returns the limit of the buffer.
	 */
	public int get_limit() {
		return limit;
	}
}

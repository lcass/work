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
	public ProgressiveBuffer(Vertex2d[] data, boolean texturised) {
		this.texturised = texturised;

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

	public void create(Vertex2d[] data, boolean texturised) {
		this.texturised = texturised;
		this.data = BufferUtils.createFloatBuffer(data.length * 2);
		this.temp = BufferUtils.createFloatBuffer(data.length * 2);
		if (texturised) {
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

	public void extend(Vertex2d[] data) {
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

	public void extend(ProgressiveBuffer indata) {
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

	}

	public FloatBuffer get_data() {
		
		return data;
	}
	public void rewind(){
		data.rewind();
	}

	public void index_put(int index, ProgressiveBuffer indata) {
		FloatBuffer temp = indata.get_data();
		temp.rewind();
		for (int i = 0; i < temp.capacity(); i++) {
			
			this.data.put(index + i, temp.get());
		}
	}

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
	public int get_limit(){
		return limit;
	}
}

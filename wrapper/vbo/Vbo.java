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
package wrapper.vbo;

import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import wrapper.graphics.Shader;
import wrapper.graphics.texture.Texture;
import wrapper.utilitys.ProgressiveBuffer;
import wrapper.utilitys.Vertex2d;

public class Vbo {
	public int vertid, texid, texcoordid, vertcount;
	private Texture tex;
	private Vertex2d transformation;
	private FloatBuffer vertex, texture;
	private Shader shader;
	private int width, height;//use these for calculating rotation , convert the point to pixel coordinate then rotate then back.
	private boolean tick = true;
	private Random r;
	private int maxlength = 0;
	private int vertexattrib, textureattrib;

	public Vbo(int width, int height, Shader shader) {
		transformation = new Vertex2d(0, 0, 1, 0);
		this.shader = shader;
		if (shader == null) {
			System.out.println("Null shader , Vbo will encounter errors!");
		}
		Vertex2d temp = new Vertex2d(width, height);
		width = (int) temp.x;
		height = (int) temp.y;
		vertexattrib = GL20.glGetAttribLocation(shader.programID, "vertex");
		textureattrib = GL20.glGetAttribLocation(shader.programID,
				"texturecoordinate");
	}

	public void create(Vertex2d[] coordinates) {
		vertex = BufferUtils.createFloatBuffer(coordinates.length * 2);
		texture = BufferUtils.createFloatBuffer(coordinates.length * 2);
		vertcount = coordinates.length;
		maxlength = vertcount;
		for (int i = 0; i < coordinates.length; i++) {
			vertex.put(coordinates[i].x);
			vertex.put(coordinates[i].y);
			texture.put(coordinates[i].u);
			texture.put(coordinates[i].v);
		}
		vertid = GL15.glGenBuffers();
		texcoordid = GL15.glGenBuffers();
		vertex.rewind();
		texture.rewind();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertex, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texcoordid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, texture, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

	}

	

	public void create(ProgressiveBuffer[] b) {// coordinates are NOT bound by
												// this

		vertex = b[0].get_data();
		texture = b[1].get_data();
		vertcount = b[0].get_data().limit();
		maxlength = vertcount;
		vertex.rewind();
		texture.rewind();
		vertid = GL15.glGenBuffers();
		texcoordid = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertex, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texcoordid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, texture, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	public void create(int size) {// coordinates are NOT bound by this
		maxlength = size;
		vertex = BufferUtils.createFloatBuffer(size);
		texture = BufferUtils.createFloatBuffer(size);
		vertcount = 0;
		vertex.rewind();
		texture.rewind();
		vertid = GL15.glGenBuffers();
		texcoordid = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertex, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texcoordid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, texture, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	public void bind_texture(Texture t) {
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		tex = t;
		this.texid = t.id;
	}

	public void edit_data(Vertex2d[] coordinates) {

		vertex = BufferUtils.createFloatBuffer(coordinates.length * 2);
		texture = BufferUtils.createFloatBuffer(coordinates.length * 2);
		vertcount = coordinates.length;
		for (int i = 0; i < coordinates.length; i++) {
			vertex.put(coordinates[i].x);
			vertex.put(coordinates[i].y);
			texture.put(coordinates[i].u);
			texture.put(coordinates[i].v);
		}
		vertex.rewind();
		texture.rewind();

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, vertex);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texcoordid);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, texture);// doesnt
																// reassing
																// meaning its
																// more
																// efficient,
																// only for
																// removing
																// objects
	}

	public void edit_data(ProgressiveBuffer[] b) {
		if (b[0].get_data() == null || b[1].get_data() == null) {
			return;
		}
		vertex.clear();
		texture.clear();
		vertex = BufferUtils.createFloatBuffer(b[0].get_data().capacity());
		texture = BufferUtils.createFloatBuffer(b[1].get_data().capacity());
		b[0].get_data().rewind();
		b[1].get_data().rewind();
		vertex = b[0].get_data();
		texture = b[1].get_data();
		vertcount = b[0].get_data().capacity();

		vertex.rewind();
		texture.rewind();

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, vertex);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texcoordid);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, texture);// doesnt
																// reassing
																// meaning its
																// more
																// efficient,
																// only for
																// removing
																// objects
	}

	public void clear_data() {
		vertex.clear();
		texture.clear();
		vertex = BufferUtils.createFloatBuffer(vertcount);
		texture = BufferUtils.createFloatBuffer(vertcount);
		vertex.rewind();
		texture.rewind();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, vertex);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texcoordid);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, texture);
		vertcount = 0;

	}

	public void rebind(int size) {
		reset();
		FloatBuffer temp = BufferUtils.createFloatBuffer(size);
		FloatBuffer temp_tex = BufferUtils.createFloatBuffer(size);
		vertex.rewind();
		maxlength = size;
		texture.rewind();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);// clear and generate
														// two new memory
														// locations with a
														// large amount of space
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, temp, GL15.GL_DYNAMIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, temp_tex, GL15.GL_DYNAMIC_DRAW);
		// reassign the previous data to this , useful for string generation.
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, vertex);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texid);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, texture);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

	}

	public void reset_count() {
		vertcount = 0;
	}

	public void dispose() {
		if (vertid != 0) {
			GL15.glDeleteBuffers(vertid);
			GL15.glDeleteBuffers(texcoordid);
		}
		if (shader != null) {
			shader.dispose();
		}
	}

	public void reset() {
		if (vertid != 0) {
			GL15.glDeleteBuffers(vertid);
			GL15.glDeleteBuffers(texcoordid);
		}
	}

	public void attach_shader(Shader shader) {
		this.shader = shader;
	}

	public void translate(Vertex2d position) {
		transformation.x += position.x;
		transformation.y += position.y;

	}

	public void set_position(Vertex2d position) {
		transformation
				.setcoords(position.x, position.y, position.u, position.v);

	}

	public void toggle_tick() {
		tick = !tick;
	}

	public void render() {
		if (vertid != -1) {

			shader.bind();
			GL11.glEnable(GL11.GL_BLEND);

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texid);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
			GL20.glEnableVertexAttribArray(vertexattrib);
			GL20.glEnableVertexAttribArray(textureattrib);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertid);
			GL20.glVertexAttribPointer(vertexattrib, 2, GL11.GL_FLOAT, false,
					0, 0);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texcoordid);
			GL20.glVertexAttribPointer(textureattrib, 2, GL11.GL_FLOAT, false,
					0, 0);

			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertcount);

			GL20.glDisableVertexAttribArray(vertexattrib);
			GL20.glDisableVertexAttribArray(textureattrib);

			GL11.glEnable(GL11.GL_BLEND);
			shader.unbind();
		} else {

			System.out.println("Error no bound vertid");
			return;
		}

	}

	public int get_vertcount() {
		return vertcount;
	}

}

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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
//Many thanks to SHC for the tutorial on this
/**
 * Texture class , this is handled automatically by the spritesheet object , do not use.
 * @author Lucas Spencer
 *
 */
public class Texture {
	public int id;
	public int width;
	public int height;
	public ByteBuffer buffer;
	private Texture(int id, int width, int height, ByteBuffer buffer) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.buffer = buffer;
	}
	public Texture(){		
	}
	public static Texture loadTexture(String name) {
		BufferedImage bimg = null;
		try {
			bimg = ImageIO.read(Texture.class.getClassLoader()
					.getResourceAsStream(name));
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("Texture loading failure! " + name);
		}
		int[] pixels = new int[bimg.getWidth() * bimg.getHeight()];
		bimg.getRGB(0, 0, bimg.getWidth(), bimg.getHeight(), pixels, 0,
				bimg.getWidth());
		ByteBuffer buffer = BufferUtils.createByteBuffer(bimg.getWidth()
				* bimg.getHeight() * 4);
		for (int y = 0; y < bimg.getHeight(); y++) {
			for (int x = 0; x < bimg.getWidth(); x++) {
				if (pixels[y * bimg.getWidth() + x] != -65281
						) {
					int pixel = pixels[y * bimg.getWidth() + x];
					buffer.put((byte) ((pixel >> 16) & 0xFF));
					buffer.put((byte) ((pixel >> 8) & 0xFF));
					buffer.put((byte) (pixel & 0xFF));
					buffer.put((byte) ((pixel >> 24) & 0xFF));
				}
				else if(pixels[y * bimg.getWidth() + x] != -1){
					buffer.put((byte)0);
					buffer.put((byte)0);
					buffer.put((byte)0);
					buffer.put((byte)0);
				}
				else{
					buffer.put((byte)-1);
					buffer.put((byte)-1);
					buffer.put((byte)-1);
					buffer.put((byte)-1);
				}
			}
		}
		buffer.flip();
		int textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, bimg.getWidth(),
				bimg.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		return new Texture(textureID, bimg.getWidth(), bimg.getHeight(),buffer);
	}
	public void bindtex(int textureID,int width , int height ,ByteBuffer buffer){
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width,
				height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
	}
}

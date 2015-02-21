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




public class Vertex2d {//2d for texture and for vert coords
	public float x,y,u,v;
	/**
	 * Create a new Vertex2d objext
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public Vertex2d(float x, float y){
		this.x = x;
		this.y = y;
		u = 0;
		v = 0;
	}
	/**
	 * Create a new Vertx2d object
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param u Texture x coordinate
	 * @param v Texture y coordinate
	 */
	public Vertex2d(float x,float y , float u , float v){
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
	}
	/**
	 * Create a new Vertex2d object int
	 * @param x X coordinate int
	 * @param y Y coordinate int
	 */
	public Vertex2d(int x, int y){
		this.x = x;
		this.y = y;
		u = 0;
		v = 0;
	}
	/**
	 * Create a new Vertex2d object
	 * @param x X coordinate int
	 * @param y Y coordinate int
	 * @param u Texture x coordinate int
	 * @param v Texture y coordinate int
	 */
	public Vertex2d(int x, int y, int u , int v){
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
	}
	/**
	 * Modify the current coordinates
	 * @param x
	 * @param y
	 * @param u
	 * @param v
	 */
	public void setcoords(float x, float y, float u , float v){
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
	}
	/**
	 * Add a vertex2d object to this
	 * @param ad Vertex2d object to add to this
	 * @return Returns this
	 */
	public Vertex2d add(Vertex2d ad){
		this.x += ad.x;
		this.y += ad.y;
		this.u += ad.u;
		this.v += ad.v;
		return this;
	}
	/**
	 * Subtract a Vertex2d object from this
	 * @param ad Vertex2d object to be subtracted
	 * @return Returns this
	 */
	public Vertex2d sub(Vertex2d ad){
		this.x -= ad.x;
		this.y -= ad.y;
		this.u -= ad.u;
		this.v -= ad.v;
		return this;
	}
	/**
	 * Multiply the relative coordinates
	 * @param ad Multiply by this x * x y * y etc
	 */
	public void mult(Vertex2d ad){
		this.x *= ad.x;
		this.y *= ad.y;
		this.u *= ad.u;
		this.v *= ad.v;
	}
	/**
	 * Multiply all coordinates by a value
	 * @param a Scalar to multiply by
	 * @return Returns this
	 */
	public Vertex2d mult(float a){
		this.x *= a;
		this.y *= a;
		this.u *= a;
		this.v *= a;
		return this;
	}
	/**
	 * Divide by the relative coordinate
	 * @param ad Divide by this x /x y/y etc
	 */
	public void div(Vertex2d ad){
		this.x /= ad.x;
		this.y /= ad.y;
		this.u /= ad.u;
		this.v /= ad.v;
	}
	/**
	 * Divide all coordinates by a value
	 * @param a A scalar to divide by
	 * @return Returns this
	 */
	public Vertex2d div(float a){
		this.x /= a;
		this.y /= a;
		this.u /= a;
		this.v /= a;
		return this;
	}
	/**
	 * Return a new identical version of this object
	 * @return Return a new Identical version of this object with xy coordinates
	 */
	public Vertex2d xy(){
		return new Vertex2d(x,y);
	}
	/**
	 * Return a new identical version of this object
	 * @return Return a new Identical version of this object with ALL coordinates
	 */
	public Vertex2d whole(){
		return new Vertex2d(x,y,u,v);
	}
	/**
	 * Set x
	 * @param x
	 */
	public void set_x(float x){
		this.x = x;
	}
	/**
	 * Set y
	 * @param y
	 */
	public void set_y(float y){
		this.y = y;
	}
	/**
	 * Set u
	 * @param u
	 */
	public void set_u(float u){
		this.u = u;
	}
	/**
	 * Set v
	 * @param v
	 */
	public void set_v(float v){
		this.v = v;
	}
}
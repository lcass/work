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
	public Vertex2d(float x, float y){
		this.x = x;
		this.y = y;
		u = 0;
		v = 0;
	}
	public Vertex2d(float x,float y , float u , float v){
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
	}
	public Vertex2d(int x, int y){
		this.x = x;
		this.y = y;
		u = 0;
		v = 0;
	}
	public Vertex2d(int x, int y, int u , int v){
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
	}
	public void setcoords(float x, float y, float u , float v){
		this.x = x;
		this.y = y;
		this.u = u;
		this.v = v;
	}
	public Vertex2d add(Vertex2d ad){
		this.x += ad.x;
		this.y += ad.y;
		this.u += ad.u;
		this.v += ad.v;
		return this;
	}
	public Vertex2d sub(Vertex2d ad){
		this.x -= ad.x;
		this.y -= ad.y;
		this.u -= ad.u;
		this.v -= ad.v;
		return this;
	}

	public void mult(Vertex2d ad){
		this.x *= ad.x;
		this.y *= ad.y;
		this.u *= ad.u;
		this.v *= ad.v;
	}
	public Vertex2d mult(float a){
		this.x *= a;
		this.y *= a;
		this.u *= a;
		this.v *= a;
		return this;
	}
	public void div(Vertex2d ad){
		this.x /= ad.x;
		this.y /= ad.y;
		this.u /= ad.u;
		this.v /= ad.v;
	}
	public Vertex2d div(float a){
		this.x /= a;
		this.y /= a;
		this.u /= a;
		this.v /= a;
		return this;
	}
	public Vertex2d xy(){
		return new Vertex2d(x,y);
	}
	public Vertex2d whole(){
		return new Vertex2d(x,y,u,v);
	}
	public void set_x(float x){
		this.x = x;
	}
	public void set_y(float y){
		this.y = y;
	}
	public void set_u(float u){
		this.u = u;
	}
	public void set_v(float v){
		this.v = v;
	}
}
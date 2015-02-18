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

public class Vertex3d {
	public float x,y,z,u,v;
	public Vertex3d(float x, float y,float z){
		this.x = x;
		this.y = y;
		this.z = z;
		u = 0;
		v = 0;
	}
	public Vertex3d(float x,float y ,float z, float u , float v){
		this.x = x;
		this.y = y;
		this.z = z;
		this.u = u;
		this.v = v;
	}
	public Vertex3d(int x, int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;
		u = 0;
		v = 0;
	}
	public Vertex3d(int x, int y,int z, int u , int v){
		this.x = x;
		this.y = y;
		this.z = z;
		this.u = u;
		this.v = v;
	}
	public void setcoords(float x, float y,float z, float u , float v){
		this.x = x;
		this.y = y;
		this.z = z;
		this.u = u;
		this.v = v;
	}

	public Vertex3d add(Vertex3d ad){
		this.x += ad.x;
		this.y += ad.y;
		this.z += ad.z;
		this.u += ad.u;
		this.v += ad.v;
		return this;
	}
	public Vertex3d sub(Vertex3d ad){
		this.x -= ad.x;
		this.y -= ad.y;
		this.z -= ad.z;
		this.u -= ad.u;
		this.v -= ad.v;
		return this;
	}
	public void mult(Vertex3d ad){
		this.x *= ad.x;
		this.y *= ad.y;
		this.z *= ad.z;
		this.u *= ad.u;
		this.v *= ad.v;
	}
	public Vertex3d mult(float a){
		this.x *= a;
		this.y *= a;
		this.z *= a;
		this.u *= a;
		this.v *= a;
		return this;
	}
	public void div(Vertex3d ad){
		this.x /= ad.x;
		this.y /= ad.y;
		this.z /= ad.z;
		this.u /= ad.u;
		this.v /= ad.v;
	}
	public Vertex3d div(float a){
		this.x /= a;
		this.y /= a;
		this.z /= a;
		this.u /= a;
		this.v /= a;
		return this;
	}
	public Vertex3d xy(){
		return new Vertex3d(x,y,z);
	}
	public Vertex3d whole(){
		return new Vertex3d(x,y,z,u,v);
	}
	public void set_x(float x){
		this.x = x;
	}
	public void set_y(float y){
		this.y = y;
	}
	public void set_z(float z){
		this.z = z;
	}
	public void set_u(float u){
		this.u = u;
	}
	public void set_v(float v){
		this.v = v;
	}
}

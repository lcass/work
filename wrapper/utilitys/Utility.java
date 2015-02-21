package wrapper.utilitys;

import wrapper.graphics.texture.spritecomponent;

public class Utility {
	//all methods in this class are static for ease of use.
	public Vertex3d ray_cast(Vertex3d source,Vertex3d target,Vertex3d[] checks){
		
		
		
		
		return null;
	}
	public static Vertex2d to_texture_coordinates(float x, float y ,float increment){
		return new Vertex2d(x * increment, y * increment);
	}
	public static Vertex2d[] square(Vertex2d source , spritecomponent s){
		return null;
	}
	public static Vertex3d[] cube(Vertex3d source , spritecomponent[] s){
		return null;
	}
	public static ProgressiveBuffer[] square_progressive(Vertex2d source , spritecomponent s){
		return null;
	}
	public static ProgressiveBuffer[] cube_progressive(){
		return null;
	}
	public static ProgressiveBuffer[] to_progressive(Vertex2d[] data){
		ProgressiveBuffer[] temp = new ProgressiveBuffer[2];
		temp[0] = new ProgressiveBuffer(data,false,false);
		temp[1] = new ProgressiveBuffer(data,true,false);
		return temp;
	}
	public static ProgressiveBuffer[] to_progressive(Vertex3d[] data){
		ProgressiveBuffer[] temp = new ProgressiveBuffer[2];
		temp[0] = new ProgressiveBuffer(data,false,true);
		temp[1] = new ProgressiveBuffer(data,true,true);
		return temp;
	}
	public Vertex2d rotate(Vertex2d data , Vertex2d rot_point , float rot){
		float cos = (float)Math.cos(rot);
		float sin = (float)Math.sin(rot);
		float nx = data.x - rot_point.x;
		float ny = data.y - rot_point.y;
		
		return new Vertex2d(((nx * cos) - (ny * sin)) + rot_point.x,((nx * sin) + (ny * cos))+ rot_point.y,data.u,data.v);
	}
	public Vertex3d rotate(Vertex3d data , Vertex3d rot_point , Vertex3d rotation){
		float cosx = (float)Math.cos(rotation.x);
		float sinx= (float)Math.sin(rotation.x);
		float cosy = (float)Math.cos(rotation.y);
		float siny= (float)Math.sin(rotation.y);
		float cosz = (float)Math.cos(rotation.z);
		float sinz= (float)Math.sin(rotation.z);
		float cx = data.x - rot_point.x;
		float cy = data.y - rot_point.y;
		float cz = data.z - rot_point.z;
		float nx = cx;
		float ny = (cy * cosx) - (cz * sinx);
		float nz = (cy * sinx) + (cz * cosx);
		cz = (nz * cosy) - (nx * siny);
		cx = (nz * siny) + (nx * cosy);
		cy = ny;
		nx = (cx * cosz) - (cy * sinz);
		ny = (cx * sinz) + (cy * cosz);
		nz = cz;
		return new Vertex3d(nx + rot_point.x,ny + rot_point.y,nz + rot_point.z,data.u,data.v);
		
	}
	public Vertex2d[] rotate(Vertex2d[] data , Vertex2d rot_point ,float rot){
		Vertex2d[] rotated = new Vertex2d[data.length];
		for(int i = 0; i < data.length;i++){
			rotated[i] = rotate(data[i],rot_point,rot);
		}
		return rotated;
	}
	public Vertex3d[] rotate(Vertex3d[] data , Vertex3d rot_point , Vertex3d rotation){
		Vertex3d[] rotated = new Vertex3d[data.length];
		for(int i = 0; i < data.length;i++){
			rotated[i] = rotate(data[i],rot_point,rotation);
		}
		return rotated;
	}
}

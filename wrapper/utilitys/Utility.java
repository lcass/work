package wrapper.utilitys;

import wrapper.graphics.texture.spritecomponent;

public class Utility {
	//all methods in this class are static for ease of use.
	/**
	 * Eh
	 * @param source
	 * @param target
	 * @param checks
	 * @return
	 */
	public Vertex3d ray_cast(Vertex3d source,Vertex3d target,Vertex3d[] checks){
		
		
		
		
		return null;
	}
	/**
	 * Convert coordinates to texture coordinates with a given increment , useful for tile sprites
	 * @param x pixel_coordinatex/increment
	 * @param y	pixel_coordinatey/increment
	 * @param increment The distance in pixels between sprites
	 * @return Returns a vertex2d object to use that can be passed to a spritesheet
	 */
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
	/**
	 * Convert Vertex2d[] data to a progressive buffer array
	 * @param data Data with texture coordinates for conversion
	 * @return Returns a new ProgressiveBuffer[] with [0] being vertex data and [1] being texture data
	 */
	public static ProgressiveBuffer[] to_progressive(Vertex2d[] data){
		ProgressiveBuffer[] temp = new ProgressiveBuffer[2];
		temp[0] = new ProgressiveBuffer(data,false,false);
		temp[1] = new ProgressiveBuffer(data,true,false);
		return temp;
	}
	/**
	 * Convert Vertex3d[] data to a progressive buffer array
	 * @param data Data with texture coordinates for conversion
	 * @return Returns a new ProgressiveBuffer[] with [0] being vertex data and [1] being texture data
	 */
	public static ProgressiveBuffer[] to_progressive(Vertex3d[] data){
		ProgressiveBuffer[] temp = new ProgressiveBuffer[2];
		temp[0] = new ProgressiveBuffer(data,false,true);
		temp[1] = new ProgressiveBuffer(data,true,true);
		return temp;
	}
	/**
	 * rotate a Vertex2d point about a point with angle (in radian)
	 * @param data Point to be rotated
	 * @param rot_point Point to be rotated about.
	 * @param rot The angle in radians to rotate by
	 * @return Returns the newly rotated point
	 */
	public Vertex2d rotate(Vertex2d data , Vertex2d rot_point , float rot){
		float cos = (float)Math.cos(rot);
		float sin = (float)Math.sin(rot);
		float nx = data.x - rot_point.x;
		float ny = data.y - rot_point.y;
		
		return new Vertex2d(((nx * cos) - (ny * sin)) + rot_point.x,((nx * sin) + (ny * cos))+ rot_point.y,data.u,data.v);
	}
	/**
	 * rotate a Vertex3d point about a point with angle (in radian)
	 * @param data Point to be rotated
	 * @param rot_point Point to be rotated about.
	 * @param rot The angles to rotate by , x y z
	 * @return Returns the newly rotated point
	 */
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
	/**
	 * Rotate an array of Vertex2d points about a point
	 * @param data Data to be rotated
	 * @param rot_point Point to be rotated about
	 * @param rot Rotation value
	 * @return Returns the newly rotated points.
	 */
	public Vertex2d[] rotate(Vertex2d[] data , Vertex2d rot_point ,float rot){
		Vertex2d[] rotated = new Vertex2d[data.length];
		for(int i = 0; i < data.length;i++){
			rotated[i] = rotate(data[i],rot_point,rot);
		}
		return rotated;
	}
	/**
	 * Rotate an array of Vertex3d points about a point
	 * @param data Data to be rotated
	 * @param rot_point Point to be rotated about
	 * @param rot Rotation value of coordinates , x y z format
	 * @return Returns the newly rotated points.
	 */
	public Vertex3d[] rotate(Vertex3d[] data , Vertex3d rot_point , Vertex3d rotation){
		Vertex3d[] rotated = new Vertex3d[data.length];
		for(int i = 0; i < data.length;i++){
			rotated[i] = rotate(data[i],rot_point,rotation);
		}
		return rotated;
	}
}

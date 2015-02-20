package wrapper.utilitys;

import wrapper.graphics.texture.spritecomponent;

public class Utility {
	//all methods in this class are static for ease of use.
	public Vertex3d ray_cast(Vertex3d source,Vertex3d target,Vertex3d[] checks){
		
		
		
		
		return null;
	}
	public static Vertex2d to_texture_coordinates(float x, float y ,float increment){
		return new Vertex2d(x * (1/increment), y * (1/increment));
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
}

attribute vec3 vertex;
attribute vec2 texturecoordinate;
uniform vec3 rotation;
uniform vec3 translation;
uniform vec3 rotation_centre;
void main()	{
	vec3 adjusted = vertex + translation;
	if(rotation.x != 0 || rotation.y != 0 || rotation.z != 0){
		adjusted -= rotation_centre;
		float cosx = cos(rotation.x);
		float sinx = sin(rotation.x);
		float cosy = cos(rotation.y);
		float siny = sin(rotation.y);
		float cosz = cos(rotation.z);
		float sinz = sin(rotation.z);
		float nx = adjusted.x;
		float ny = (adjusted.y * cosx) - (adjusted.z * sinx);
		float nz = (adjusted.y * sinx) + (adjusted.z * cosx);
		float cz = (nz * cosy) - (nx * siny);
		float cx = (nz * siny) + (nx * cosy);
		float cy = ny;
		nx = (cx * cosz) - (cy * sinz);
		ny = (cx * sinz) + (cy * cosz);
		nz = cz;
		adjusted = vec3(nx,ny,nz) + rotation_centre;
	}
	vec4 mvv = gl_ModelViewMatrix * vec4(adjusted, 1.0);
	gl_Position = gl_ProjectionMatrix *  mvv;
}

package M;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;





public class PicturePreview extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7740148182143228008L;

    private final static int WIDTH = 450;
    private final static int HEIGHT = 450;
    

    
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    //图片路径
	  	String name = "Picture\\" + Model.pictureId +".jpg";
	  	//获取图像
	  	ImageIcon imageIcon = new ImageIcon(name);
	  	Image image = imageIcon.getImage();
		
		//绘制压缩后的图片
		g.drawImage(image,0,0,WIDTH,HEIGHT,this);
	}
	
	

	 

}

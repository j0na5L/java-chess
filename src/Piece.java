
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public abstract class Piece extends JFrame{
   
   private static final long serialVersionUID = 1585138483326743198L;
   
   String name;
   int row;
   int col;
   char color;
   ImageIcon ic;
   boolean hasMoved;
   
   
   public Piece(String pieceName, char color, boolean hasMoved){
      
      this.name = color + pieceName;
      this.ic = new ImageIcon(createImageAdress(this.name));
      this.color = color;
      this.hasMoved = hasMoved;
   }
   
   
   abstract boolean validMove(int oldPosition[], int newPosition[], ChessButton[][] boardLabels);
   
   
   abstract void squareInCheck(int[] startPos, int newPos[], ArrayList<String> checkList, ChessButton[][] boardLabels);
   
   
   public String createImageAdress(String imageName){
      String image = ("./images/" + imageName + ".png");
      return image;
   }
   
   
   @Override
   public String getName(){
      return this.name;
   }
   
   
   public ImageIcon getImage(){
      return this.ic;
   }
   
   
   public boolean isAlive(boolean pieceStatus){
      this.hasMoved = pieceStatus;
      return this.hasMoved;
   }
   
   
   public char getColor(){
      return this.color;
   }
   
}

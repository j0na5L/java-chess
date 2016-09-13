
import java.util.ArrayList;


public class Rook extends Piece{
   
   private static final long serialVersionUID = -342779601973587680L;
   
   private boolean isValid;
   // private int[] pieceInTheWay;
   
   
   public Rook(char color, boolean hasMoved){
      super("Rook", color, hasMoved);
   }
   
   
   @Override
   boolean validMove(int[] oldPosition, int[] newPosition, ChessButton boardLabels[][]){
      
      boolean horizontalCheck = false;
      boolean emptyPath = false;
      boolean verticalCheck = false;
      boolean moveLeft = false;
      boolean moveRight = false;
      boolean moveUp = false;
      boolean moveDown = false;
      int yNew = newPosition[0];
      int xNew = newPosition[1];
      int yOld = oldPosition[0];
      int xOld = oldPosition[1];
      int yCount = yOld;
      int xCount = xOld;
      Piece pieceColor = boardLabels[yOld][xOld].getPiece();
      
      if(xNew == xOld && yNew < yOld){
         verticalCheck = true;
         moveUp = true;
      }else if(xNew == xOld && yNew > yOld){
         verticalCheck = true;
         moveDown = true;
      }else{
         // do nothing..
      }
      if(xNew < xOld && yNew == yOld){
         horizontalCheck = true;
         moveLeft = true;
      }else if(xNew > xOld && yNew == yOld){
         horizontalCheck = true;
         moveRight = true;
      }else{
         // doNOThing atm...
      }
      if(horizontalCheck == true && moveRight == true){
         for(int i = 1; xCount + i <= xNew; i++){
            Piece p = boardLabels[yOld][xCount + i].getPiece();
            if(p == null){
               emptyPath = true;
            }else if(p.getColor() != pieceColor.getColor() && xCount + i == xNew){
               emptyPath = true;
               break;
            }else{
               emptyPath = false;
               break;
            }
         }
      }else if(horizontalCheck == true && moveLeft == true){
         for(int i = 1; xCount - i >= xNew; i++){
            Piece p = boardLabels[yCount][xCount - i].getPiece();
            if(p == null){
               emptyPath = true;
            }else if(p.getColor() != pieceColor.getColor() && xCount - i == xNew){
               emptyPath = true;
               break;
            }else{
               emptyPath = false;
               break;
            }
         }
      }else if(verticalCheck == true && moveUp == true){
         for(int i = 1; yCount - i >= yNew; i++){
            Piece p = boardLabels[yCount - i][xOld].getPiece();
            if(p == null){
               emptyPath = true;
            }else if(p.getColor() != pieceColor.getColor() && yCount - i == yNew){
               emptyPath = true;
               break;
            }else{
               emptyPath = false;
               break;
            }
         }
      }else if(verticalCheck == true && moveDown == true){
         for(int i = 1; yCount + i <= yNew; i++){
            Piece p = boardLabels[yCount + i][xCount].getPiece();
            if(p == null){
               emptyPath = true;
            }else if(p.getColor() != pieceColor.getColor() && yCount + i == yNew){
               emptyPath = true;
               break;
            }else{
               emptyPath = false;
               break;
            }
         }
      }
      if(emptyPath == true){
         this.isValid = true;
      }else{
         this.isValid = false;
      }
      
      return this.isValid;
   }
   
   
   public boolean hasMoved(boolean moved){
      this.hasMoved = moved;
      return this.hasMoved;
   }
   
   
   @Override
   void squareInCheck(int[] oldPosition, int[] newPosition, ArrayList<String> checkList, ChessButton[][] boardLabels){
      
      int yOld = oldPosition[0];
      int xOld = oldPosition[1];
      int yCount = yOld;
      int xCount = xOld;
      for(int i = 1; xCount + i < boardLabels.length; i++){
         Piece p = boardLabels[yCount][xCount + i].getPiece();
         if(p == null){
            // The path is clear
            String s = yCount + ";" + (xCount + i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
         }else{
            String s = yCount + ";" + (xCount + i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
            break;
         }
      }
      for(int i = 1; xCount - i >= 0; i++){
         Piece p = boardLabels[yCount][xCount - i].getPiece();
         if(p == null){
            // The path is clear
            String s = yCount + ";" + (xCount - i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
         }else{
            String s = yCount + ";" + (xCount - i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
            break;
         }
      }
      for(int i = 1; yCount - i >= 0; i++){
         Piece p = boardLabels[yCount - i][xCount].getPiece();
         if(p == null){
            // The path is clear
            String s = (yCount - i) + ";" + xCount;
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
         }else{
            String s = (yCount - i) + ";" + xCount;
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
            break;
         }
      }
      for(int i = 1; yCount + i < boardLabels.length; i++){
         Piece p = boardLabels[yCount + i][xCount].getPiece();
         if(p == null){
            // The path is clear
            String s = (yCount + i) + ";" + xCount;
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
         }else{
            String s = (yCount + i) + ";" + xCount;
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
            break;
         }
      }
   }
}
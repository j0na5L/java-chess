
import java.util.ArrayList;


public class Queen extends Piece{
   
   private static final long serialVersionUID = 5220881850903065884L;
   
   private boolean isValid;
   // private int[] pieceInTheWay;
   
   
   public Queen(char color, boolean hasMoved){
      super("Queen", color, hasMoved);
   }
   
   
   @Override
   boolean validMove(int[] oldPosition, int[] newPosition, ChessButton boardLabels[][]){
      
      boolean horizontalCheck = false;
      boolean emptyPath = false;
      boolean verticalCheck = false;
      boolean upRight = false;
      boolean upLeft = false;
      boolean downRight = false;
      boolean downLeft = false;
      boolean moveLeft = false;
      boolean moveRight = false;
      boolean moveUp = false;
      boolean moveDown = false;
      
      int yNew = newPosition[0];
      int xNew = newPosition[1];
      int yOld = oldPosition[0];
      int xOld = oldPosition[1];
      int xCount = xOld;
      int yCount = yOld;
      
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
      for(int i = 0; i < boardLabels.length; i++){
         if(xOld + i == xNew && yOld + i == yNew){
            downRight = true;
         }else if(xOld - i == xNew && yOld - i == yNew){
            upLeft = true;
         }else if(xOld + i == xNew && yOld - i == yNew){
            upRight = true;
         }else if((xOld - i) == xNew && (yOld + i) == yNew){
            downLeft = true;
         }
      }
      
      Piece pieceColor = boardLabels[yCount][xCount].getPiece();
      // Loop until our coordinates match the new coordinates
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
      }else if(upRight == true){
         for(int i = 1; (yCount - i >= yNew && xCount + i <= xNew); i++){
            Piece p = boardLabels[yCount - i][xCount + i].getPiece();
            if(p == null){
               // The path is clear
               emptyPath = true;
            }else if(yNew == (yCount - i) && xNew == (xCount + i) && p.getColor() != pieceColor.getColor()){
               // Then we can take the piece
               emptyPath = true;
               break;
            }else{
               emptyPath = false;
               // The path is blocked, as either the piece is the same colour or the end coordinates is behind the piece.
               break;
            }
         }
      }else if(downLeft == true){
         for(int i = 1; (yCount + i <= yNew && xCount - i >= xNew); i++){
            Piece p = boardLabels[yCount + i][xCount - i].getPiece();
            if(p == null){
               // The path is clear
               emptyPath = true;
            }else if(yNew == (yCount + i) && xNew == (xCount - i) && p.getColor() != pieceColor.getColor()){
               // Then we can take the piece
               emptyPath = true;
               break;
            }else{
               emptyPath = false;
               // The path is blocked, as either the piece is the same colour or the end coordinates is behind the piece.
               break;
            }
         }
      }else if(downRight == true){
         for(int i = 1; (yCount + i <= yNew && xCount + i <= xNew); i++){
            Piece p = boardLabels[yCount + i][xCount + i].getPiece();
            if(p == null){
               // The path is clear
               emptyPath = true;
            }else if(yNew == (yCount + i) && xNew == (xCount + i) && p.getColor() != pieceColor.getColor()){
               // Then we can take the piece
               emptyPath = true;
               break;
            }else{
               emptyPath = false;
               // The path is blocked, as either the piece is the same colour or the end coordinates is behind the piece.
               break;
            }
         }
      }else if(upLeft == true){
         for(int i = 1; (yCount - i >= yNew && xCount - i >= xNew); i++){
            Piece p = boardLabels[yCount - i][xCount - i].getPiece();
            if(p == null){
               // The path is clear
               emptyPath = true;
            }else if(yNew == (yCount - i) && xNew == (xCount - i) && p.getColor() != pieceColor.getColor()){
               // Then we can take the piece
               emptyPath = true;
               break;
            }else{
               emptyPath = false;
               // The path is blocked, as either the piece is the same colour or the end coordinates is behind the piece.
               break;
            }
         }
      }
      if(emptyPath == true){
         this.isValid = true;
      }else{
         this.isValid = false;
      }
      System.out.println(this.isValid);
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
      int xCount = xOld;
      int yCount = yOld;
      
      for(int i = 1; (yCount - i >= 0 && xCount + i < boardLabels.length); i++){
         Piece p = boardLabels[yCount - i][xCount + i].getPiece();
         if(p == null){
            // The path is clear
            String s = (yCount - i) + ";" + (xCount + i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
         }else{
            String s = (yCount - i) + ";" + (xCount + i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
            break;
         }
      }
      for(int i = 1; (yCount + i < boardLabels.length && xCount - i >= 0); i++){
         Piece p = boardLabels[yCount + i][xCount - i].getPiece();
         if(p == null){
            // The path is clear
            String s = (yCount + i) + ";" + (xCount - i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
         }else{
            String s = (yCount + i) + ";" + (xCount - i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
            break;
         }
      }
      for(int i = 1; (yCount + i < boardLabels.length && xCount + i < boardLabels.length); i++){
         Piece p = boardLabels[yCount + i][xCount + i].getPiece();
         if(p == null){
            // The path is clear
            String s = (yCount + i) + ";" + (xCount + i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
         }else{
            String s = (yCount + i) + ";" + (xCount + i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
            break;
         }
      }
      for(int i = 1; (yCount - i >= 0 && xCount - i >= 0); i++){
         Piece p = boardLabels[yCount - i][xCount - i].getPiece();
         if(p == null){
            // The path is clear
            String s = (yCount - i) + ";" + (xCount - i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
         }else{
            String s = (yCount - i) + ";" + (xCount - i);
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
            
            }
            break;
         }
      }
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
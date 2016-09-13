
import java.util.ArrayList;


public class Bishop extends Piece{
   
   private static final long serialVersionUID = 1596865406990544424L;
   
   private boolean isValid;
   
   
   public Bishop(char color, boolean hasMoved){
      super("Bishop", color, hasMoved);
   }
   
   
   @Override
   boolean validMove(int[] oldPosition, int[] newPosition, ChessButton boardLabels[][]){
      
      boolean emptyPath = false;
      boolean upRight = false;
      boolean upLeft = false;
      boolean downRight = false;
      boolean downLeft = false;
      
      int yNew = newPosition[0];
      int xNew = newPosition[1];
      int yOld = oldPosition[0];
      int xOld = oldPosition[1];
      int xCount = xOld;
      int yCount = yOld;
      
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
      if(upRight == true){
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
   }
}
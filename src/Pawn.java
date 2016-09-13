
import java.util.ArrayList;


public class Pawn extends Piece{
   
   private static final long serialVersionUID = 5780856715906932604L;
   
   public boolean isValid = false;
   private char w = 'w';
   private char b = 'b';
   
   
   public Pawn(char color, boolean hasMoved){
      super("Pawn", color, hasMoved);
   }
   
   
   @Override
   boolean validMove(int[] oldPosition, int[] newPosition, ChessButton boardLabels[][]){
      
      boolean emptyPawnMove = false;
      boolean emptyPath = false;
      boolean enemyPawnMove = false;
      
      int yNew = newPosition[0];
      int xNew = newPosition[1];
      int yOld = oldPosition[0];
      int xOld = oldPosition[1];
      Piece pColor = boardLabels[yOld][xOld].getPiece();
      Piece p = boardLabels[yNew][xNew].getPiece();
      if(p == null){
         emptyPawnMove = true;
      }else if(p.getColor() != pColor.getColor()){
         enemyPawnMove = true;
      }
      int xMove = xOld - xNew;
      int yMove = yOld - yNew;
      
      if(enemyPawnMove == true){
         if(pColor.getColor() == this.w){
            if(yMove == 1 && (xMove == 1 || xMove == -1)){
               emptyPath = true;
               this.hasMoved = true;
            }
         }else if(pColor.getColor() == this.b){
            if(yMove == -1 && (xMove == 1 || xMove == -1)){
               emptyPath = true;
               this.hasMoved = true;
            }
         }else{
            emptyPath = false;
         }
      }else if(emptyPawnMove == true && this.hasMoved == false){
         if(pColor.getColor() == this.w){
            if(yMove == 1 || yMove == 2){
               Piece pCheck = boardLabels[yOld - 1][xOld].getPiece();
               if(pCheck == null){
                  emptyPath = true;
                  this.hasMoved = true;
               }else{
                  emptyPath = false;
                  this.hasMoved = false;
               }
            }
         }else{
            if(xOld == xNew && yMove == -1 || yMove == -2){
               Piece pCheck = boardLabels[yOld + 1][xOld].getPiece();
               if(pCheck == null){
                  emptyPath = true;
                  this.hasMoved = true;
               }else{
                  emptyPath = false;
                  this.hasMoved = false;
               }
            }
         }
      }else if(emptyPawnMove == true && this.hasMoved == true){
         if(pColor.getColor() == this.w){
            if(yMove == 1 && xOld == xNew){
               emptyPath = true;
               this.hasMoved = true;
            }else{
               emptyPath = false;
            }
         }else{
            if(yMove == -1 && xOld == xNew){
               emptyPath = true;
               this.hasMoved = true;
            }else{
               emptyPath = false;
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
   
   
   @Override
   void squareInCheck(int[] startPos, int[] newPos, ArrayList<String> checkList, ChessButton[][] boardLabels){
      
      int xOld = startPos[1];
      int yOld = startPos[0];
      int[] xMoves = {1, -1};
      int xPos;
      Piece p = boardLabels[yOld][xOld].getPiece();
      if(p.getColor() == 'w'){
         int yPos = yOld - 1;
         for(int i = 0; i < xMoves.length; i++){
            xPos = xOld + xMoves[i];
            if(xPos < 0 || xPos > 7){
            
            }else{
               String s = yPos + ";" + xPos;
               if(checkList.contains(s) == false){
                  checkList.add(s);
               }else{
               }
            }
         }
      }else{
         int yPos = yOld + 1;
         for(int i = 0; i < xMoves.length; i++){
            xPos = xOld + xMoves[i];
            if(xPos < 0 || xPos > 7){
            
            }else{
               String s = yPos + ";" + xPos;
               if(checkList.contains(s) == false){
                  checkList.add(s);
               }else{
               }
            }
         }
      }
   }
}

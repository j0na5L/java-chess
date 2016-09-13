
import java.util.ArrayList;


public class Knight extends Piece{
   
   private static final long serialVersionUID = 8430999436880583465L;
   
   private boolean isValid;
   private int[][] aKnightsMove = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
   
   
   public Knight(char color, boolean isValid){
      super("Knight", color, isValid);
   }
   
   
   // boolean validMove(int oldXy[], int newXy[], char color){
   //
   // return this.isValid;
   // }
   
   @Override
   boolean validMove(int[] oldPosition, int[] newPosition, ChessButton boardLabels[][]){
      
      boolean emptyPath = false;
      int yNew = newPosition[0];
      int xNew = newPosition[1];
      int yOld = oldPosition[0];
      int xOld = oldPosition[1];
      Piece pColor = boardLabels[yOld][xOld].getPiece();
      
      int xMove = xOld - xNew;
      int yMove = yOld - yNew;
      System.out.println("EmptyPath: " + emptyPath);
      if(xMove == 1 || xMove == -1 || xMove == 2 || xMove == -2){
         if(xMove == 1){
            if(yMove == -2 || yMove == 2){
               Piece p = boardLabels[yNew][xNew].getPiece();
               if(p == null){
                  emptyPath = true;
               }else if(p.getColor() != pColor.getColor()){
                  emptyPath = true;
               }else{
                  emptyPath = false;
               }
            }
         }else if(xMove == -1){
            if(yMove == -2 || yMove == 2){
               Piece p = boardLabels[yNew][xNew].getPiece();
               if(p == null){
                  emptyPath = true;
               }else if(p.getColor() != pColor.getColor()){
                  emptyPath = true;
               }else{
                  emptyPath = false;
               }
            }
         }else if(xMove == 2){
            if(yMove == 1 || yMove == -1){
               Piece p = boardLabels[yNew][xNew].getPiece();
               if(p == null){
                  emptyPath = true;
               }else if(p.getColor() != pColor.getColor()){
                  emptyPath = true;
               }else{
                  emptyPath = false;
               }
            }
         }else if(xMove == -2){
            if(yMove == 1 || yMove == -1){
               Piece p = boardLabels[yNew][xNew].getPiece();
               if(p == null){
                  emptyPath = true;
               }else if(p.getColor() != pColor.getColor()){
                  emptyPath = true;
               }else{
                  emptyPath = false;
               }
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
   
   
   @Override
   void squareInCheck(int[] oldPosition, int[] newPosition, ArrayList<String> checkList, ChessButton[][] boardLabels){
      int[] xyMove;
      int xOld = oldPosition[1];
      int yOld = oldPosition[0];
      int xMove;
      int yMove;
      int yPos;
      int xPos;
      for(int i = 0; i < this.aKnightsMove.length; i++){
         xyMove = this.aKnightsMove[i];
         yMove = xyMove[0];
         xMove = xyMove[1];
         yPos = yOld - yMove;
         xPos = xOld - xMove;
         if(yPos < 0 || yPos > 7 || xPos > 7 || xPos < 0){
            // outside the board
         }else{
            String s = yPos + ";" + xPos;
            if(checkList.contains(s) == false){
               checkList.add(s);
            }else{
               // already exist in the list
            }
         }
      }
   }
}
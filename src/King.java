
import java.util.ArrayList;


public class King extends Piece{
   
   private static final long serialVersionUID = -6781605390208465276L;
   
   private boolean isValid;
   // private int[] pieceInTheWay;
   
   
   public King(char color, boolean hasMoved){
      super("King", color, hasMoved);
   }
   
   
   @Override
   boolean validMove(int[] oldPosition, int[] newPosition, ChessButton boardLabels[][]){
      
      boolean emptyPath = false;
      int yNew = newPosition[0];
      int xNew = newPosition[1];
      int yOld = oldPosition[0];
      int xOld = oldPosition[1];
      
      int xMove = xOld - xNew;
      int yMove = yOld - yNew;
      Piece pieceColor = boardLabels[yOld][xOld].getPiece();
      // m�ste �ven kolla om rutan �r i checkList
      if(xMove == 1 || xMove == -1 || xMove == 0){
         if(yMove == 1 || yMove == -1 || yMove == 0){
            Piece p = boardLabels[yNew][xNew].getPiece();
            if(p == null){
               emptyPath = true;
            }else if(p != null && p.getColor() != pieceColor.getColor()){
               emptyPath = true;
            }else if(p.getColor() == pieceColor.getColor()){
               emptyPath = false;
            }else{
               emptyPath = false;
            }
         }
      }
      
      System.out.println(emptyPath);
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
      int yNew = newPosition[0];
      int xNew = newPosition[1];
      int yOld = oldPosition[0];
      int xOld = oldPosition[1];
      
      int xMove = xOld - xNew;
      int yMove = yOld - yNew;
      
   }
}
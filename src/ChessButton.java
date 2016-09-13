import javax.swing.JButton;


/** ChessButton
 * 
 * @author Mathias Hildebrand
 * @version 20111101 */
public class ChessButton extends JButton{
   
   private static final long serialVersionUID = -8631375706735642117L;
   
   private int[] grid = new int[2];
   private Piece piece;
   
   
   public ChessButton(int x, int y){
      
      this.grid[0] = x;
      this.grid[1] = y;
   }
   
   
   public void setPiece(Piece pc){
      if(pc != null){
         setIcon(pc.getImage());
      }else{
         setIcon(null);
      }
      this.piece = pc;
   }
   
   
   public Piece getPiece(){
      return this.piece;
   }
}

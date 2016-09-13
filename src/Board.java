import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;


/** Board
 * 
 * @author Mathias Hildebrand
 * @version 20111101 */
/** @author Jonas Lydmark */
public class Board extends JFrame{
   
   private static final long serialVersionUID = -2935724498392192787L;
   
   private ChessButton[][] boardLabels = new ChessButton[8][8];
   private LinkedList<Piece> wPieces = new LinkedList<Piece>();
   private LinkedList<Piece> bPieces = new LinkedList<Piece>();
   private JPanel panelA;
   public ArrayList<String> whiteCheck = new ArrayList<String>();
   public ArrayList<String> blackCheck = new ArrayList<String>();
   private int i, j;
   private Piece clickedSquare;
   private Piece pieceToMove;
   private boolean playerTurns = true;
   private boolean whiteInCheck = false;
   private boolean blackInCheck = false;
   private boolean checkMateWhite = false;
   private boolean checkMateBlack = false;
   int yStart;
   int yEnd;
   int xStart;
   int xEnd;
   int yxOld[];
   private char color;
   
   public static final int FRAME_WIDTH = 300;
   public static final int FRAME_HEIGHT = 200;
   public static final int FRAME_X_ORIGIN = 150;
   public static final int FRAME_Y_ORIGIN = 250;
   
   private JButton playAgain;
   private JButton quitButton;
   
   
   /** Constructor for Board */
   public Board(){
      // add pieces to list
      setLayout(new FlowLayout());
      this.panelA = new JPanel(new GridLayout(8, 8));
      this.panelA.setPreferredSize(new Dimension(700, 700));
      Border bdrA = BorderFactory.createEtchedBorder();
      Border txtBrdA = BorderFactory.createTitledBorder(bdrA, "Panel A");
      this.panelA.setBorder(txtBrdA);
      
      // Create the lists of pieces and the board
      setUpBoard();
      setUpPieces();
      
      // Create the window
      add(this.panelA);
      setSize(800, 800);
      setLocation(0, 0);
      setResizable(false);
      setTitle("Chess");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
      
      runSquareInCheck();
   }
   
   
   /** This method should create the pieces on startup */
   private void setUpPieces(){
      char b = 'b';
      char w = 'w';
      
      Piece wPawn1 = new Pawn(w, false);
      Piece wPawn2 = new Pawn(w, false);
      Piece wPawn3 = new Pawn(w, false);
      Piece wPawn4 = new Pawn(w, false);
      Piece wPawn5 = new Pawn(w, false);
      Piece wPawn6 = new Pawn(w, false);
      Piece wPawn7 = new Pawn(w, false);
      Piece wPawn8 = new Pawn(w, false);
      
      Piece wRook1 = new Rook(w, false);
      Piece wRook2 = new Rook(w, false);
      
      Piece wBishop1 = new Bishop(w, false);
      Piece wBishop2 = new Bishop(w, false);
      
      Piece wKnight1 = new Knight(w, false);
      Piece wKnight2 = new Knight(w, false);
      Piece wKing = new King(w, false);
      Piece wQueen = new Queen(w, false);
      
      Piece bPawn1 = new Pawn(b, false);
      Piece bPawn2 = new Pawn(b, false);
      Piece bPawn3 = new Pawn(b, false);
      Piece bPawn4 = new Pawn(b, false);
      Piece bPawn5 = new Pawn(b, false);
      Piece bPawn6 = new Pawn(b, false);
      Piece bPawn7 = new Pawn(b, false);
      Piece bPawn8 = new Pawn(b, false);
      
      Piece bRook1 = new Rook(b, false);
      Piece bRook2 = new Rook(b, false);
      
      Piece bBishop1 = new Bishop(b, false);
      Piece bBishop2 = new Bishop(b, false);
      
      Piece bKnight1 = new Knight(b, false);
      Piece bKnight2 = new Knight(b, false);
      
      Piece bKing = new King(b, false);
      Piece bQueen = new Queen(b, false);
      
      // white pieces to be added to white linked list
      this.wPieces.add(wRook1);
      this.wPieces.add(wKnight1);
      this.wPieces.add(wBishop1);
      
      this.wPieces.add(wQueen);
      this.wPieces.add(wKing);
      
      this.wPieces.add(wBishop2);
      this.wPieces.add(wKnight2);
      this.wPieces.add(wRook2);
      
      this.wPieces.add(wPawn1);
      this.wPieces.add(wPawn2);
      this.wPieces.add(wPawn3);
      this.wPieces.add(wPawn4);
      this.wPieces.add(wPawn5);
      this.wPieces.add(wPawn6);
      this.wPieces.add(wPawn7);
      this.wPieces.add(wPawn8);
      
      // Black pieces to be added to black Linked list
      this.bPieces.add(bRook1);
      this.bPieces.add(bKnight1);
      this.bPieces.add(bBishop1);
      this.bPieces.add(bQueen);
      this.bPieces.add(bKing);
      
      this.bPieces.add(bBishop2);
      this.bPieces.add(bKnight2);
      this.bPieces.add(bRook2);
      
      this.bPieces.add(bPawn1);
      this.bPieces.add(bPawn2);
      this.bPieces.add(bPawn3);
      this.bPieces.add(bPawn4);
      this.bPieces.add(bPawn5);
      this.bPieces.add(bPawn6);
      this.bPieces.add(bPawn7);
      this.bPieces.add(bPawn8);
      
      for(Piece bPiece : this.bPieces){
         if(this.bPieces.indexOf(bPiece) < 8){
            this.boardLabels[0][this.bPieces.indexOf(bPiece)].setPiece(bPiece);
         }else{
            this.boardLabels[1][this.bPieces.indexOf(bPiece) - 8].setPiece(bPiece);
         }
      }
      
      for(Piece wPiece : this.wPieces){
         if(this.wPieces.indexOf(wPiece) < 8){
            this.boardLabels[7][this.wPieces.indexOf(wPiece)].setPiece(wPiece);
         }else{
            this.boardLabels[6][this.wPieces.indexOf(wPiece) - 8].setPiece(wPiece);
         }
      }
   }
   
   
   /** Method used for drawing the board */
   private void setUpBoard(){
      // Build the board with white/black buttons
      char c = 'w';
      
      for(this.i = 0; this.i <= 7; this.i++){
         for(this.j = 0; this.j <= 7; this.j++){
            this.boardLabels[this.i][this.j] = new ChessButton(this.i, this.j);
            // Set the String sent at click as coordinates
            String ac = this.i + " " + this.j;
            this.boardLabels[this.i][this.j].setActionCommand(ac);
            
            this.boardLabels[this.i][this.j].setPreferredSize(new Dimension(20, 20));
            this.boardLabels[this.i][this.j].setVisible(true);
            this.boardLabels[this.i][this.j].setOpaque(true);
            
            if(c == 'w'){
               this.boardLabels[this.i][this.j].setBackground(Color.white);
               c = 'b';
            }else{
               this.boardLabels[this.i][this.j].setBackground(Color.black);
               c = 'w';
            }
            // Adding behavior to the panels
            this.boardLabels[this.i][this.j].addActionListener(new ActionListener(){
               
               public void actionPerformed(ActionEvent e){
                  // When a panel is clicked it sends a String
                  clickedPanel(e.getActionCommand());
               }
            });
            this.panelA.add(this.boardLabels[this.i][this.j]);
         }
         if(c == 'w') c = 'b';
         else c = 'w';
      }
   }
   
   
   private boolean isInChess(char color, int[] yx){
      
      int yPos = yx[0];
      int xPos = yx[1];
      boolean validMove = false;
      String position = yPos + ";" + xPos;
      if(color == 'w'){
         if(this.whiteCheck.contains(position) == false){
            validMove = true;
         }else{
            validMove = false;
         }
         
      }else{
         if(this.blackCheck.contains(position) == false){
            validMove = true;
         }else{
            validMove = false;
         }
      }
      return validMove;
   }
   
   
   /** Simple method for writing out end game message and who won, also if you want to play again or quit.
    * 
    * @param color - what colour that loss the game */
   private void gameOver(char color){
      
      if(color == 'w'){
         setTitle("Black Won!");
      }else{
         setTitle("White Won");
      }
      quit();
   }
   
   
   private void quit(){
      System.exit(0);
   }
   
   
   /** @param color - what colour for the turn
    * @return movingTime - returns whos turn it is, false for b and true for w */
   boolean playerTurn(char color){
      boolean movingTime;
      
      if(color == 'w'){
         movingTime = true;
         
      }else{
         movingTime = false;
         
      }
      return movingTime;
   }
   
   
   /** Set the behavior when a panel is clicked. If an icon is present the location is saved for move. if an icon is selected and an empty panel is chosen, move. If no icon is
    * selected the restart selection process
    * 
    * @param in - string that contains the square you clicked at. */
   private void clickedPanel(String in){
      // Set the clicked panel, The panel returns a String on the form X Y
      // To get the position we extract the numbers from this String
      char b = 'b';
      int yx[] = {Integer.parseInt(in.charAt(0) + ""), Integer.parseInt(in.charAt(2) + "")};
      
      this.clickedSquare = this.boardLabels[yx[0]][yx[1]].getPiece();
      if(this.clickedSquare != null && this.pieceToMove == null){
         
         this.pieceToMove = this.clickedSquare;
         this.color = this.pieceToMove.getColor();
         playerTurn(this.color);
         this.yxOld = yx;
         this.yStart = yx[0];
         this.xStart = yx[1];
      }else if(this.clickedSquare == null && this.pieceToMove != null && playerTurn(this.color) == this.playerTurns){
         this.yEnd = yx[0];
         this.xEnd = yx[1];
         System.out.println("\t" + "X" + "\t" + "Y");
         System.out.println("From: " + "\t" + this.xStart + "\t" + this.yStart);
         System.out.println("To:" + "\t" + this.xEnd + "\t" + this.yEnd);
         
         if(true == this.pieceToMove.validMove(this.yxOld, yx, this.boardLabels) && (this.pieceToMove instanceof King == false || isInChess(this.color, yx) == false)){
            if(this.pieceToMove.getColor() == 'w'){
               squareInCheck(this.yxOld, yx, this.whiteCheck);
               movePiece(yx);
            }else{
               squareInCheck(this.yxOld, yx, this.blackCheck);
               movePiece(yx);
            }
         }else{
            System.out.println("Board 293");
            this.pieceToMove = null;
         }
      }
      
      else if(this.clickedSquare != null && this.pieceToMove != null && this.pieceToMove.validMove(this.yxOld, yx, this.boardLabels) == true && playerTurn(this.color) == this.playerTurns && (this.pieceToMove instanceof King == false || isInChess(this.color, yx) == false)){
         if(this.clickedSquare.getColor() == this.pieceToMove.getColor()){
            System.out.println("Invalid move, same colour on pieces");
            this.pieceToMove = null;
            this.clickedSquare = null;
         }else if(this.wPieces.contains(this.clickedSquare) == true){
            this.wPieces.remove(this.clickedSquare);
            squareInCheck(this.yxOld, yx, this.blackCheck);
            movePiece(yx);
         }else{
            this.bPieces.remove(this.clickedSquare);
            squareInCheck(this.yxOld, yx, this.whiteCheck);
            movePiece(yx);
         }
      }else{
         System.out.println("Invalid Move, its just wrong!");
         this.pieceToMove = null;
         this.clickedSquare = null;
      }
   }
   
   
   /** Method to move a piece
    * 
    * @param xy - new position for the piece [int xy] */
   public void movePiece(int[] xy){
      
      this.boardLabels[this.yxOld[0]][this.yxOld[1]].setPiece(null);
      this.boardLabels[this.yxOld[0]][this.yxOld[1]].setIcon(null);
      this.boardLabels[xy[0]][xy[1]].setPiece(this.pieceToMove);
      this.whiteCheck.clear();
      this.blackCheck.clear();
      this.pieceToMove = null;
      runSquareInCheck();
      
      if(this.playerTurns == false){
         
         this.playerTurns = true;
      }else{
         this.playerTurns = false;
         
      }
      this.pieceToMove = null;
      this.clickedSquare = null;
      
      repaint();
   }
   
   
   /** Method to check which pieces they hold in "chess". */
   public void runSquareInCheck(){
      this.whiteCheck.clear();
      this.blackCheck.clear();
      int wKingPos[] = new int[2];
      int bKingPos[] = new int[2];
      
      int wPos[] = new int[2];
      int bPos[] = new int[2];
      for(int i = 0; i < this.boardLabels.length; i++){
         for(int j = 0; j < this.boardLabels.length; j++){
            Piece p = this.boardLabels[i][j].getPiece();
            if(p == null){
               // do nothing
            }else{
               if(this.wPieces.contains(p)){
                  if(p instanceof King){
                     wKingPos[0] = i;
                     wKingPos[1] = j;
                  }
                  wPos[0] = i;
                  wPos[1] = j;
                  int[] startPos = wPos;
                  p.squareInCheck(startPos, wPos, this.whiteCheck, this.boardLabels);
               }else{
                  if(p instanceof King){
                     bKingPos[0] = i;
                     bKingPos[1] = j;
                  }
                  bPos[0] = i;
                  bPos[1] = j;
                  int[] startPos = bPos;
                  p.squareInCheck(startPos, bPos, this.blackCheck, this.boardLabels);
               }
            }
         }
      }
      kingChecking(wKingPos, bKingPos);
   }
   
   
   private void kingChecking(int[] wKingPos, int[] bKingPos){
      String wCheck = wKingPos[0] + ";" + wKingPos[1];
      String bCheck = bKingPos[0] + ";" + bKingPos[1];
      if(this.blackCheck.contains(wCheck) == true && this.whiteInCheck == false){
         this.whiteInCheck = true;
         System.out.println("White King in Chess");
      }else if(this.blackCheck.contains(wCheck) == true && this.whiteInCheck == true){
         this.checkMateWhite = true;
         gameOver('w');
      }else if(this.blackCheck.contains(wCheck) == false){
         this.whiteInCheck = false;
      }
      if(this.whiteCheck.contains(bCheck) == true && this.blackInCheck == false){
         this.blackInCheck = true;
         System.out.println("Black King in Chess");
      }else if(this.whiteCheck.contains(bCheck) == true && this.blackInCheck == true){
         this.checkMateBlack = true;
         gameOver('b');
      }else if(this.whiteCheck.contains(bCheck) == false){
         this.blackInCheck = false;
      }
      
   }
   
   
   /** @param startPos - startposition for a piece
    * @param newPos - new position for the piece
    * @param checkList - a list with squares the king is in chess on. */
   public void squareInCheck(int[] startPos, int[] newPos, ArrayList<String> checkList){
      this.pieceToMove.squareInCheck(startPos, newPos, checkList, this.boardLabels);
   }
   
   
   /** Main method
    * 
    * @param args */
   public static void main(String[] args){
      try{
         UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }catch(ClassNotFoundException e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }catch(InstantiationException e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }catch(IllegalAccessException e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }catch(UnsupportedLookAndFeelException e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
      Board b = new Board();
   }
   
}

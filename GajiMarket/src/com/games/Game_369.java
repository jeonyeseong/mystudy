package com.games;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Game_369 {
	public static boolean end = false;
	private int point = 0; // 포인트
	
   Scanner sc = new Scanner(System.in);

   public int Start() {
      while (true) {

         System.out.println("---------- 369게임 ----------");
         System.out.println("1. 게임 시작");
         System.out.println("2. 게임 방법");
         System.out.println("0. 게임 종료");
         System.out.print("선택 : ");
         int choice = sc.nextInt();

         switch (choice) {
            case 1:
               startGame();
               break;
            case 2:
               rule();
               break;
            case 0:
               System.out.println("===== 게임 종료 =====");
               return point;
            default:
               System.out.println("입력 오류!!");
               System.out.println("재입력 요망!!");
         }
      }
   }

   public void startGame() {

	   System.out.println("===== 게임 시작 =====");
	   
	   int me = 0;
	   int com = 0;
	   int turn = 1; // 0 사람 1 컴퓨터
	   String temp = "";
	   end = false;
	   
	   while (!end) 
	   {
		   
		   com = me + 1;
		   
		   if (turn == 1) 
		   {
			   System.out.print("COM : ");
			   
			   // com 369 "짝"
			   String comAnswer = answer(com);
			   if (check(com, comAnswer)) 
			   {
				   System.out.println(comAnswer);
				   turn = 0;
			   }
			   else
			   {
				   System.out.println(com);
				   turn = 0;
			   }
		   }

		   if (turn == 0) 
		   {
			   System.out.print("ME : ");
			   temp = sc.next();
			   
			   
			   // me가 "짝"해도 1 증가
			   if (temp.contains("짝")) 
			   {
				   if (check(com + 1, temp)) 
				   {
					   turn = 1;
					   me = com + 1;
				   } 
				   else 
				   {
					   end = true;
					   System.out.println("===== 게임 종료 =====");
					   System.out.println("컴퓨터의 승리입니다.");
					   gameOver(com);
				   }
			   } 
			   else 
			   {
				   // me가 "짝" 대신 숫자 입력
				   try {
					   me = Integer.parseInt(temp);
					   if(String.valueOf(me).contains("3") || String.valueOf(me).contains("6") || String.valueOf(me).contains("9"))
					   {
						   end = true;
						   System.out.println("===== 게임 종료 =====");
						   System.out.println("컴퓨터의 승리입니다.");
						   gameOver(com);

						   break;
					   }
					   // me가 숫자 입력 오류
					   if (me != com + 1) 
					   {
						   end = true;
						   System.out.println("===== 게임 종료 =====");
						   System.out.println("컴퓨터의 승리입니다.");
						   gameOver(com);

						   break;
					   }
				   } 
				   catch (NumberFormatException e) 
				   {
					   end = true;
					   System.out.println("===== 게임 종료 =====");
					   System.out.println("컴퓨터의 승리입니다.");
					   gameOver(com);
					   break;
				   } 
			   }
			   turn = 1;   
		   } 
	   }
   }

   public void rule() {
      System.out.println("===== 게임 방법 =====");
      System.out.println("1. 컴퓨터와 돌아가면서 숫자를 입력한다.");
      System.out.println("2. 3, 6, 9가 들어간 양의 정수일 때는 숫자 대신 짝 이라고 입력한다.");
      System.out.println("3. 만약 3, 6, 9 중 두개 이상이 포함되면 그 수만큼 짝 이라고 입력한다.");
 	  System.out.println("4. 3초 이내에 입력하지 못하면 패배한다.");
   }

   public String answer(int count) {
      StringBuilder str = new StringBuilder();
      String strCount = String.valueOf(count);
      for (int i = 0; i < strCount.length(); i++) 
      {
         char check = strCount.charAt(i);
         if (check == '3' || check == '6' || check == '9') 
         {
            str.append("짝");
         }
      }
      return str.toString();
   }

   public boolean check(int count, String result) 
   {
      String temp = answer(count);
      if (!temp.isEmpty() && temp.equals(result)) 
      {
         return true;
      } 
      else 
      {
         return false;
      }
   }

   public void gameOver(int com) {
	   point += com;
   }
 
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fer
 */
package ventana;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Stack;
import java.util.Random;

public class ventana {
    public int filas=100;
    public int columnas=100;
    public int iniciox = 0;
    public int inicioy = 0;
    public int finx = 0;
    public int finy = 0;
    static int numero=0;
    public int [][]Raddom_Matrix = new int[filas][columnas];
    public char [][]maz = new char[filas][columnas];
  /*public char [][]maz =
    {
      {'F', '0', '0', '#', '#', '0', '#', '0', '0', '0'},
      {'#', '#', '0', '0', '0', '0', '0', '0', '0', '0'},
      {'#', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
      {'0', '0', '0', '#', '0', '0', '0', '0', '#', '0'},
      {'0', '#', '0', '0', '0', '0', '0', '0', '0', '0'},
      {'0', '0', '0', '#', '#', '0', '#', '0', '0', '0'},
      {'#', '#', '0', '0', '0', '0', '0', '0', '0', '0'},
      {'#', '0', '0', '0', '0', '0', '0', 'I', '0', '0'},
      {'0', '0', '0', '#', '0', '0', '0', '0', '0', '0'},
      {'0', '#', '0', '0', '0', '0', '0', '0', '0', '0'}
    };*/
  /*  char [][]maz =
{
  {'F', '0', '0', '#'},
  {'#', '#', '0', '0'},
  {'#', '0', '0', '0'},
  {'0', '#', '0', '#'},
  {'0', 'I', '0', '0'}
};*/
    public boolean [][] visitado =  new boolean[filas][columnas];       
    public int []movx = {0,0,-1,1};  
    public int []movy = {-1,1,0,0}; 
    public int []dist = {0,0,0,0};
    public double []heuristica = {0,0,0,0};
    public int []nx = {0,0,0,0};
    public int []ny = {0,0,0,0};
    public int k = 0;
    
    public void dibuja_ventana(Graphics g, int r, int c, char [][]maz){
        filas = r;
        columnas = c;
        
        g.setColor( new Color(255,255,255) );
        g.fillRect(0,0,500,500);
        maz = asignar_maze(maz);
        dibujar_obstaculos(g,maz);
        
    }
    
    void dibujar_obstaculos(Graphics g,char [][] meze)
  {
               
     g.setColor (Color.BLACK);
      for(int i = 0; i < filas; i = i + 1)
        { 
            g.drawLine (0, i*(500/filas), 500, i*(500/filas));
        }
      for(int i = 0; i < columnas; i = i + 1)
        {
        g.drawLine (i*(500/columnas), 0, i*(500/columnas), 500);
        }
      for(int i= 0; i<filas; i++)
      {
          for(int j=0; j<columnas; j++)
          {
              if(meze[i][j]=='#')
              {
                  g.setColor (Color.BLACK);
               g.fillRect(j*(500/columnas),i*(500/filas),(500/columnas),(500/filas));   
              }
              if(meze[i][j]=='I')//estado inicial
              {
                 g.setColor (Color.BLUE);
                  g.fillRect((j*(500/columnas))+((500/columnas)/3),(i*(500/filas))+((500/filas)/3),((500/columnas)/3),((500/filas)/3)); 
              }
              if(meze[i][j]=='F')//estado final
              {
                  g.setColor (Color.RED);
                  g.fillRect((j*(500/columnas))+((500/columnas)/3),(i*(500/filas))+((500/filas)/3),((500/columnas)/3),((500/filas)/3)); 
              }
          }
      }
  }
    
    public char [][]Laberinto_Random(int f, int c)
    {
        filas = f;
        columnas = c;
         Random r = new Random();
        for (int i=0; i < filas; i++) {
            for (int j=0; j < columnas; j++) {
                    Raddom_Matrix[i][j] = (int) (Math.random()*9+1);
                    if(Raddom_Matrix[i][j] >3)
                    {
                        Raddom_Matrix[i][j] = 0;
                        maz[i][j]='0';
                    }
                    else
                    {
                        maz[i][j] = '#';
                    }
                   
  }
}
         maz[0][0] = 'F';
         maz[filas -1 ][columnas-1] = 'I';
        return maz;
    }
    public void getInicio(char [][]maz)
    {
      for(int i = 0; i<filas; i++)
      {
          for(int j=0; j<columnas; j++)
          {
              if(maz[i][j]=='I')
              {
                  iniciox=i;
                  inicioy = j;
              }
          }
      }
  }
  
    public void getFin(char [][]maz)
    {
      for(int i = 0; i<filas; i++)
      {
          for(int j=0; j<columnas; j++)
          {
              if(maz[i][j]=='F')
              {
                  finx=i;
                  finy = j;
              }
          }
      }
  }
    
    public void movimiento_objeto(Graphics g)
   {
     /* g2D.setColor (Color.WHITE);
      g2D.fillRect((Yini*(getWidth()/columnas))+((getWidth()/columnas)/3),(iniX*(getHeight()/filas))+((getHeight()/filas)/3),((getWidth()/columnas)/3),((getHeight()/filas)/3)); 
    */
     for(int i=0; i<filas; i++)
     { for(int j=0; j<columnas; j++)
         {
                 if(visitado[i][j]==true)
                 {
                 g.setColor (Color.ORANGE);
      g.fillRect((j*(500/columnas))+((500/columnas)/3),(i*(500/filas))+((500/filas)/3),((500/columnas)/3),((500/filas)/3)); 
   }
         }
     }
      g.setColor (Color.BLUE);
      g.fillRect((inicioy*(500/columnas))+((500/columnas)/3),(iniciox*(500/filas))+((500/filas)/3),((500/columnas)/3),((500/filas)/3)); 
      
  }
    
   void ordenar_dist()
   {
    int aux, aux1, aux2;
    for(int j =0; j<k/2; j++)
    {
    for(int i=0; i<k-1; i++)
        {
            if(dist[i]>dist[i+1])
            {
                aux = dist[i];
                dist[i] = dist[i+1];
                dist[i+1]=aux;

                aux1 = nx[i];
                nx[i] = nx[i+1];
                nx[i+1] = aux1;

                aux2 = ny[i];
                ny[i] = ny[i+1];
                ny[i+1] =aux2;
            }
        }
    }
}
   
    void ordenar_dist2()
   {
    double aux;
    int aux1, aux2;
    for(int j =0; j<k/2; j++)
    {
    for(int i=0; i<k-1; i++)
        {
            if(heuristica[i]>heuristica[i+1])
            {
                aux = heuristica[i];
                heuristica[i] = heuristica[i+1];
                heuristica[i+1]=aux;

                aux1 = nx[i];
                nx[i] = nx[i+1];
                nx[i+1] = aux1;

                aux2 = ny[i];
                ny[i] = ny[i+1];
                ny[i+1] =aux2;
            }
        }
    }
}
   char [][]asignar_maze(char [][]aux)
   {
       char [][]aux2 = new char[filas][columnas];
       for (int i=0; i<filas; i++)
       {
           for(int j=0; j<columnas; j++)
           {
               aux2[i][j]=aux[i][j];
           }
       }
           return aux;
   }
   public int DFS(char [][]lab){  
    
       maz = asignar_maze(lab);
   getInicio(maz);
   Stack<estado> cola = new Stack<estado>();
   estado inicial = new estado(iniciox,inicioy,0);
   cola.push(inicial);         // El estado inicial es el primero en procesar
    
    
    while(!cola.isEmpty()){       // Mientras existan estados por revisar
       estado actual = cola.peek();       // Sacamos el estado a procesar
      
        cola.pop();
        if (maz[actual.x][actual.y] == 'F'){   // Con 'F' como caracter que marca la salida
          return actual.d;        // Regresamos el número de pasos dados
        }
 visitado[actual.x][actual.y] = true;    // Marcamos como visitada la casilla actual
        for (int i=0; i<4; i++){                // Probamos por validar los posibles cuatro movimientos
            int nuevox = actual.x + movx[i];    // Modificamos con posibles cambios en x
            int nuevoy = actual.y + movy[i];    // Modificamos con posibles cambios en y
     
            if (nuevox >= 0 && nuevox < filas && nuevoy >= 0 && nuevoy < columnas){    // Revisamos que esté en los límites
                if (!visitado[nuevox][nuevoy] && maz[nuevox][nuevoy] != '#'){  // Revisamos que no esté visitado y que no sea pared
                    estado nuevo = new estado(nuevox, nuevoy, actual.d + 1);
                    cola.push(nuevo);
                }
            }
        }
         
    }
   return -1; // Si no encontró una forma de llegar al objetivo, regresamos un número control conocido
    }

   public int BestFS(char [][]lab)
   {
       maz = asignar_maze(lab);
       getInicio(maz);
       getFin(maz);
    Stack<estado> cola = new Stack<estado>();
    
    estado inicial = new estado(iniciox,inicioy,0);
    cola.push(inicial);       
    
    while(!cola.isEmpty()){
         estado actual = cola.peek();
         cola.pop();
         if (maz[actual.x][actual.y] == 'F'){
            return actual.d; 
         }
         visitado[actual.x][actual.y] = true;
         for (int i=0; i<4; i++){                
            int nuevox = actual.x + movx[i];    
            int nuevoy = actual.y + movy[i];        
            if (nuevox >= 0 && nuevox < filas && nuevoy >= 0 && nuevoy < columnas){    
                if (!visitado[nuevox][nuevoy] && maz[nuevox][nuevoy] != '#'){  
                    dist[k]=Math.abs(finx-nuevox)+Math.abs(finy-nuevoy);
                    nx[k] = nuevox;
                    ny[k] = nuevoy;
                    k++;
                }
            }
      
            
        }
        ordenar_dist();
       for(int i=k-1; i>=0;i--)
       {
           estado nuevo = new estado(nx[i], ny[i], actual.d + 1);
           
           cola.push(nuevo);
          // cout<<"\n"<<dist[i]<<"\t";
           dist[i]=0;
           nx[i]=0;
           ny[i]=0;

       }
       k = 0; 
    }
      return -1;
  }
   
   public int AStart(char [][]lab)
   { maz = asignar_maze(lab);
       getInicio(maz);
       getFin(maz);
    Stack<estado> cola = new Stack<estado>();
    
    estado inicial = new estado(iniciox,inicioy,0);
    cola.push(inicial);       
    
    while(!cola.isEmpty()){
         estado actual = cola.peek();
         cola.pop();
         if (maz[actual.x][actual.y] == 'F'){
            return actual.d; 
         }
         visitado[actual.x][actual.y] = true;
         for (int i=0; i<4; i++){                
            int nuevox = actual.x + movx[i];    
            int nuevoy = actual.y + movy[i];        
            if (nuevox >= 0 && nuevox < filas && nuevoy >= 0 && nuevoy < columnas){    
                if (!visitado[nuevox][nuevoy] && maz[nuevox][nuevoy] != '#'){  
                    heuristica[k] = Math.sqrt((nuevox-finx)*(nuevox-finx)+(nuevoy-finy)*(nuevoy-finy)) + actual.d+1;
                    nx[k] = nuevox;
                    ny[k] = nuevoy;
                    k++;
                }
            }
      
        }
        ordenar_dist2();
       for(int i=k-1; i>=0;i--)
       {
           estado nuevo = new estado(nx[i], ny[i], actual.d + 1);
           
           cola.push(nuevo);
          // cout<<"\n"<<dist[i]<<"\t";
           dist[i]=0;
           nx[i]=0;
           ny[i]=0;

       }
       k = 0; 
    }
      return -1;
  }
   
   private static class estado {
    public int x;  // Coordenada en x
    public int y;  // Coordenada en y
    public int d;  // Distancia recorrida
    public estado(int ix, int iy, int id) {
             x = ix; y = iy; d = id;
        }
    }
}

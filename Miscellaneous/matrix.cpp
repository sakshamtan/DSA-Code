#include <iostream>
#include <vector>

using namespace std;

void display(vector<vector<int>> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        for (int j = 0; j < arr[0].size(); j++)
        {
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
}

void input(vector<vector<int>> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        for (int j = 0; j < arr[0].size(); j++)
        {
            cin >> arr[i][j];
        }
    }
}

void waveH(vector<vector<int>> &arr)
{
    for (int row = 0; row < arr.size(); row++)
    {
        if (row % 2 == 0)
        {
            for (int col = 0; col < arr[0].size(); col++)
            {
                cout << arr[row][col] << " ";
            }
        }
        else
        {
            for (int col = arr[0].size() - 1; col >= 0; col--)
                cout << arr[row][col] << " ";
        }
    }
    cout << endl;
}

void waveV(vector<vector<int>> &arr)
{
    for (int col = 0; col < arr[0].size(); col++)
    {
        if (col % 2 == 0)
        {
            for (int row = 0; row < arr.size(); row++)
            {
                cout << arr[row][col] << " ";
            }
        }
        else
        {
            for (int row = arr.size() - 1; row >= 0; row--)
                cout << arr[row][col] << " ";
        }
    }
    cout << endl;
}

void exitPoint(vector<vector<int>> &arr)
{
    int dir = 0;
    int r = 0;
    int c = 0;
    while (true)
    {
        dir = (dir + arr[r][c]) % 4;
        if (dir == 0) //east
        {
            c++;
            if (c == arr[0].size())
            {
                cout << r << " , " << (c - 1);
                break;
            }
        }
        if (dir == 1) //south
        {
            r++;
            if (r == arr.size())
            {
                cout << (r - 1) << " , " << c;
                break;
            }
        }
        if (dir == 2) //west
        {
            c--;
            if (c == -1)
            {
                cout << r << " , " << (c + 1);
                break;
            }
        }
        if (dir == 3) //north
        {
            r--;
            if (r == -1)
            {
                cout << (r + 1) << " , " << c;
                break;
            }
        }
    }
}

void spiralPrint(vector<vector<int>> &arr)
{
    int minc = 0;
    int minr = 0;
    int maxc = arr[0].size() - 1;
    int maxr = arr.size() - 1;
    int tne = arr.size() * arr[0].size();
    while (tne != 0)
    {
        for (int i = minc; i <= maxc; i++)
        {
            cout << arr[minr][i] << " ";
            tne--;
        }
        minr++;

        for (int i = minr; i <= maxr; i++)
        {
            cout << arr[i][maxc] << " ";
            tne--;
        }
        maxc--;

        for (int i = maxc; i >= minc; i--)
        {
            cout << arr[maxr][i] << " ";
            tne--;
        }
        maxr--;

        for (int i = maxr; i >= minr; i--)
        {
            cout << arr[i][minc] << " ";
            tne--;
        }
        minc++;
    }
}

void transpose(vector<vector<int>> &arr)
{
    for (int i = 0; i < arr.size(); i++)
    {
        for (int j = i; j < arr[0].size(); j++) //if (j = 0) to ek cheez do baar swap ho jaayegi.
        {
            swap(arr[i][j], arr[j][i]);
        }
    }
}

void swapR(vector<vector<int>> &arr)
{
    int r1 = 0;
    int r2 = arr.size() - 1;
    while (r2 > r1)
    {
        swap(arr[r1], arr[r2]);
        r1++;
        r2--;
    }
}

void swapC(vector<vector<int>> &arr)
{
    int c1 = 0;
    int c2 = arr[0].size() - 1;
    while (c2 > c1)
    {
        for (int r = 0; r < arr.size(); r++)
        {
            swap(arr[r][c1], arr[r][c2]);
        }
        c1++;
        c2--;
    }
}

void rotate_90(vector<vector<int> > &arr, bool isClockwise)
{
    transpose(arr);
    if (isClockwise)
    {
        swapC(arr);
    }
    else
    {
        swapR(arr);
    }
}

int main(int args, char **argv)
{
    cout << "please enter row and column numbers:";
    int n, m;
    cin >> n >> m;
    vector<vector<int> > arr(n, vector<int>(m, 0));
    input(arr);
    // display(arr);
    // transpose(arr);
    // waveH(arr);
    // waveV(arr);
    // exitPoint(arr);
    // spiralPrint(arr);
    // swapR(arr);
    // swapC(arr);
    rotate_90(arr, false);
    display(arr);

    return 0;
}
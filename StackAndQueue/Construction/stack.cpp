using namespace std;

class stack
{
    private:
    int* arr;
    int tos;
    int NoOfElements;
    int maxCapacity;

    protected:
    void initialize(int size)
    {
        this->arr = new int[size];
        this->tos = -1;
        this->NoOfElements = 0;
        this->maxCapacity = size;
    }

    void stackEmptyException()
    {
        if(this->NoOfElements == 0)
        throw("StackIsEmpty");
    }

    void stackOverFlowException()
    {
        if(this->NoOfElements == this->maxCapacity)
        throw("stackOverFlow");
    }

    void push_(int data)
    {
        this->arr[++this->tos] = data;
        this->NoOfElements++;
    }

    int top_()
    {
        return this->arr[this.tos];
    }

    int pop_()
    {
        int rv = this->arr[this->tos];
        this->arr[this->tos] = 0;
        this->NoOfElements--;

        return rv;
    }

    public:
    int size()
    {
        return this->NoOfElements;
    }

    bool isEmpty()
    {
        return this->NoOfElements == 0;
    }

    void push(int data)
    {
        stackOverFlowException();
        push_(data);
    }

    int top()
    {
        stackEmptyException();
        return top_();
    }

    int pop()
    {
        stackEmptyException();
        return pop_();
    }
};
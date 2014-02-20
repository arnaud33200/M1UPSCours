#include <memory>

template <class T> class AutoPtr
{
	T* ptr;
public:
	AutoPtr(T* p = 0) : ptr(p) {};
	~AutoPtr() { delete ptr; }
	T* operator->() { return ptr; }
	T& operator*() { return *ptr; }
	/* data */
};
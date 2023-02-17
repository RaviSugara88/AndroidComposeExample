package com.sdd.saniproadvance.retrofit.util

import com.sdd.saniproadvance.retrofit.Post


sealed class ApiState{

    class Success(val data:List<Post> ) : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()

}

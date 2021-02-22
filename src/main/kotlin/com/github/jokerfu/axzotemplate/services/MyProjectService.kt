package com.github.jokerfu.axzotemplate.services

import com.github.jokerfu.axzotemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

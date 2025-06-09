<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/"><img alt="Logotipo de LogiRail" src="/images/logo.png" height="40"></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li <%= request.getRequestURI().endsWith("index.jsp")?"class='active'":"" %>><a href="/">Inicio <span class="sr-only">(current)</span></a></li>
        <li <%= request.getRequestURI().contains("/actor")?"class='active'":"" %>><a href="/actores">actores</a></li>
        <li <%= request.getRequestURI().endsWith("actor-list.jsp")?"class='active'":"" %>><a href="/categorias">categorias</a></li>
        <li <%= request.getRequestURI().endsWith("acercade.jsp")?"class='active'":"" %>><a href="/ajax">AJAX</a></li>
        <li><a href="pagina/bien/educada">Saluda</a></li>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="acercade.jsp">Acerca de</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

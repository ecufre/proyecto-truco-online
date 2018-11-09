<!-- 
Si soy admin
Tengo que poder:
Agregar gente
Remover gente
Crear parejas
Crear partidas

Todos deben poder:
Ver el ranking del grupo cerrado
 -->

<!-- Si es admin -->
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">Administracion del grupo</div>
		<div class="col-md-6">
			<div class="row">
				<div class="input-group">
					<input class="form-control" placeholder="Agergar jugador al grupo"
						id="apodo"> <span class="form-group input-group-btn">
						<button class="btn btn-default" type="button">Agregar</button>
					</span>
				</div>
			</div>
			<div class="row">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>Apodo</th>
								<th>Clasificacion</th>
								<th>Accion</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Fer</td>
								<td>Experto</td>
								<td><button class="btn btn-default" type="button">Remover</button></td>
							</tr>
							<tr>
								<td>Ced</td>
								<td>Experto</td>
								<td><button class="btn btn-default" type="button">Remover</button></td>
							</tr>
							<tr>
								<td>Ale</td>
								<td>Experto</td>
								<td><button class="btn btn-default" type="button">Remover</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="row">
				<label>Crear Pareja</label>
				<form action="" class="form-inline">
					<div class="form-group">
						<select class="form-control">
							<option>Fer</option>
							<option>Ced</option>
							<option>Ale</option>
						</select> <select class="form-control">
							<option>Fer</option>
							<option>Ced</option>
							<option>Ale</option>
						</select>
						<button class="btn btn-default" type="button">Crear pareja</button>
					</div>
				</form>
			</div>
			<div class="row"></div>
		</div>
	</div>
</div>
<!-- fin del menu de admin -->
<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">Ranking del grupo</div>
	</div>
</div>
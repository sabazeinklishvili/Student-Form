# Run once: .\copy_car_images.ps1
# Deletes placeholder XML first, then copies real PNG photos from assets.
$ErrorActionPreference = "Stop"
$src = Join-Path $PSScriptRoot "..\assets"
$dst = Join-Path $PSScriptRoot "app\src\main\res\drawable"
New-Item -ItemType Directory -Force -Path $dst | Out-Null
$pairs = @(
  @{ From = "c__Users_User_AppData_Roaming_Cursor_User_workspaceStorage_empty-window_images_BMW_M3-85d62904-459e-4c4b-acd3-56b1035dd00f.png"; To = "car_bmw_m3_f80.png" },
  @{ From = "c__Users_User_AppData_Roaming_Cursor_User_workspaceStorage_empty-window_images_Mercedes-Benz_CLS-a76da862-8dd3-4cff-891c-a7b3f4c67dda.png"; To = "car_mercedes_cls_class.png" },
  @{ From = "c__Users_User_AppData_Roaming_Cursor_User_workspaceStorage_empty-window_images_Porsche_911_GT3_rs-82db518c-c0fb-423e-8456-1c81de5d64e1.png"; To = "car_porsche_911_gt3_rs.png" },
  @{ From = "c__Users_User_AppData_Roaming_Cursor_User_workspaceStorage_empty-window_images_Ferrari_488_spider-e3fa4e6e-7145-4897-9095-cc9ac98524f5.png"; To = "car_ferrari_488_spider.png" }
)
foreach ($p in $pairs) {
  $xmlPlaceholder = Join-Path $dst ($p.To -replace '\.png$','.xml')
  if (Test-Path -LiteralPath $xmlPlaceholder) { Remove-Item -LiteralPath $xmlPlaceholder -Force }
  $fromPath = Join-Path $src $p.From
  $toPath = Join-Path $dst $p.To
  if (-not (Test-Path -LiteralPath $fromPath)) { throw "Missing: $fromPath" }
  Copy-Item -LiteralPath $fromPath -Destination $toPath -Force
  Write-Host "OK:" $p.To
}
Write-Host "Done."

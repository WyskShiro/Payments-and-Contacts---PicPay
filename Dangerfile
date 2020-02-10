# including in a project's CHANGELOG for example
declared_trivial = github.pr_title.include? "#trivial"

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

# Warn when there is a big PR
warn("Merge Request way too big") if git.lines_of_code > 500

if github.pr_body.length < 5
  fail "Please provide a summary in the Pull Request description"
end

require 'json'

# Reads the XML generated from lint

doc = JSON.parse(File.read("/home/runner/work/DesafioPicPay/DesafioPicPay/presentation/build/reports/ktlint/ktlintMainSourceSetCheck.json"))
doc.each do |result|
result["errors"].each do |error|
  file = result["file"].split(module_name)
  file.shift
  file = module_name + file.join(module_name)
  message = error["message"]
  line = error["line"]
  fail(message, file: file, line: line)
 end
end
